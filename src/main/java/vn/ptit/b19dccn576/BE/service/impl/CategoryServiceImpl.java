package vn.ptit.b19dccn576.BE.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.ptit.b19dccn576.BE.dto.CategoryDto;
import vn.ptit.b19dccn576.BE.dto.CategoryResDto;
import vn.ptit.b19dccn576.BE.exception.ResourceNotFoundException;
import vn.ptit.b19dccn576.BE.model.Category;
import vn.ptit.b19dccn576.BE.repository.CategoryRepository;
import vn.ptit.b19dccn576.BE.repository.TypeRepository;
import vn.ptit.b19dccn576.BE.service.ICategoryService;

import java.util.Date;
import java.util.List;

@Service
public class CategoryServiceImpl implements ICategoryService {
    private CategoryRepository categoryRepository;
    private TypeRepository typeRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, TypeRepository typeRepository) {
        this.categoryRepository = categoryRepository;
        this.typeRepository = typeRepository;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category save(CategoryDto category) {
        var existedType = typeRepository
                .findById(category.getTypeId())
                .orElseThrow(() -> new ResourceNotFoundException("Type", "id", category.getTypeId()));
        var cate = Category.builder()
                .type(existedType)
                .name(category.getName())
                .percentage(category.getPercentage())
                .appliedDate(category.getAppliedDate())
                .createdDate(new Date())
                .lastModifiedDate(new Date())
                .build();
        return categoryRepository.save(cate);
    }

    @Override
    public CategoryResDto update(CategoryDto category, String id) {
        var idNumber = Long.parseLong(id);
        var existedCategory = categoryRepository
            .findById(idNumber)
            .orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
        existedCategory.setPercentage(category.getPercentage());
        existedCategory.setName(category.getName());
        existedCategory.setAppliedDate(category.getAppliedDate());

        var savedCate = categoryRepository.save(existedCategory);

        var response = CategoryResDto.builder()
                .id(savedCate.getId())
                .name(savedCate.getName())
                .percentage(savedCate.getPercentage())
                .appliedDate(savedCate.getAppliedDate())
                .createdDate(savedCate.getCreatedDate())
                .lastModifiedDate(savedCate.getLastModifiedDate())
                .build();

        return response;
    }

    @Override
    public List<Category> getCategoryByTypeAndMonth(String month, String year, String type) {
        return categoryRepository.getCategoriesByTypeAndMonth(type, year, month);
    }
}
