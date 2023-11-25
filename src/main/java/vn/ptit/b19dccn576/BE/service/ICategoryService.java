package vn.ptit.b19dccn576.BE.service;

import vn.ptit.b19dccn576.BE.dto.CategoryDto;
import vn.ptit.b19dccn576.BE.dto.CategoryResDto;
import vn.ptit.b19dccn576.BE.model.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> getAllCategories();

    Category save(CategoryDto category);

    void save(List<Category> categories);

    CategoryResDto update(CategoryDto category, String id);

    List<Category> getCategoryByTypeAndMonth(String month, String year, String type);
}
