package vn.ptit.b19dccn576.BE.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.ptit.b19dccn576.BE.dto.ItemDto;
import vn.ptit.b19dccn576.BE.dto.ItemResDto;
import vn.ptit.b19dccn576.BE.exception.ResourceNotFoundException;
import vn.ptit.b19dccn576.BE.model.Category;
import vn.ptit.b19dccn576.BE.model.Item;
import vn.ptit.b19dccn576.BE.model.User;
import vn.ptit.b19dccn576.BE.repository.CategoryRepository;
import vn.ptit.b19dccn576.BE.repository.ItemRepository;
import vn.ptit.b19dccn576.BE.repository.UserRepository;
import vn.ptit.b19dccn576.BE.service.IItemService;


import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class ItemServiceImpl implements IItemService {
    private CategoryRepository categoryRepository;
    private ItemRepository itemRepository;
    private UserRepository userRepository;

    @Autowired
    public ItemServiceImpl(CategoryRepository categoryRepository, ItemRepository itemRepository, UserRepository userRepository) {
        this.categoryRepository = categoryRepository;
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Page<ItemResDto> getAllItems(Pageable pageable, int typeId) {
        return itemRepository.findByCategoryTypeId(pageable, (long) typeId).map(ele -> ItemResDto.builder()
                .id(ele.getId())
                .name(ele.getName())
                .merchandise(ele.getMerchandise())
                .categoryName(ele.getCategory().getName())
                .price(ele.getPrice())
                .quantity(ele.getQuantity())
                .createdDate(ele.getCreatedDate())
                .lastModifiedDate(ele.getLastModifiedDate())
                .build());
    }

    @Override
    public Item save(ItemDto itemDto) {
        var existedCategory = getCategory(itemDto.getCategoryId());
        var existedUser = getUser(itemDto.getUserId());
        var item = Item.builder()
                .name(itemDto.getName())
                .price(itemDto.getPrice())
                .merchandise(itemDto.getMerchandise())
                .quantity(itemDto.getQuantity())
                .category(existedCategory)
                .user(existedUser)
                .createdDate(new Date())
                .lastModifiedDate(new Date())
                .build();
        return itemRepository.save(item);
    }

    @Override
    public ItemResDto update(ItemDto itemDto, String id) {
        var idNumber = Long.parseLong(id);
        var existedItem = itemRepository
                .findById(idNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Item", "id", id));
        var existedCategory = getCategory(itemDto.getCategoryId());
        var existedUser = getUser(itemDto.getUserId());

        existedItem.setName(itemDto.getName());
        existedItem.setPrice(itemDto.getPrice());
        existedItem.setMerchandise(itemDto.getMerchandise());
        existedItem.setQuantity(itemDto.getQuantity());
        existedItem.setCategory(existedCategory);
        existedItem.setUser(existedUser);
        existedItem.setLastModifiedDate(new Date());

        itemRepository.save(existedItem);
        var itemRes = ItemResDto.builder()
                .name(existedItem.getName())
                .price(existedItem.getPrice())
                .merchandise(existedItem.getMerchandise())
                .quantity(existedItem.getQuantity())
                .categoryName(existedCategory.getName())
                .userName(existedUser.getFirstName() + existedUser.getLastName())
                .lastModifiedDate(existedItem.getLastModifiedDate())
                .createdDate(existedItem.getCreatedDate())
                .build();
        return itemRes;
    }

    @Override
    public List<Item> getItemsByTypeAndMonth(String month, String year, String type) {
        log.info("[ItemServiceImpl] getItemsByTypeAndMonth with month {}, year {}, type {}", month, year, type);
        return itemRepository.getItemByTypeAndMonth(type, year, month);
    }

    @Override
    public List<Item> getItemsByTypeAndYear(String year, String type) {
        log.info("[ItemServiceImpl] getItemsByTypeAndMonth with year {}, type {}", year, type);
        return itemRepository.getItemByTypeAndYear(year, type);
    }

    private Category getCategory(Long id){
        return categoryRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
    }

    private User getUser(Long id){
        return userRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
    }
}
