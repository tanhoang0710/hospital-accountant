package vn.ptit.b19dccn576.BE.service;

import vn.ptit.b19dccn576.BE.dto.CategoryPercentageResDto;
import vn.ptit.b19dccn576.BE.dto.ItemDto;
import vn.ptit.b19dccn576.BE.dto.ItemResDto;
import vn.ptit.b19dccn576.BE.model.Item;

import java.util.List;

public interface IItemService {
    List<Item> getAllItems();
    Item save(ItemDto item);
    ItemResDto update(ItemDto itemDto, String id);
    List<Item> getItemsByTypeAndMonth(String month, String year, String type);
    List<Item> getItemsByTypeAndYear(String year, String type);
}
