package vn.ptit.b19dccn576.BE.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vn.ptit.b19dccn576.BE.common.BaseResponse;
import vn.ptit.b19dccn576.BE.dto.CategoryDto;
import vn.ptit.b19dccn576.BE.dto.CategoryResDto;
import vn.ptit.b19dccn576.BE.model.Category;
import vn.ptit.b19dccn576.BE.service.ICategoryService;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @GetMapping()
    public BaseResponse<List<Category>> getAllUsers(){
        return BaseResponse.ofSucceeded(categoryService.getAllCategories());
    }

    @PostMapping()
    public BaseResponse<Category> saveRole(@Validated @RequestBody CategoryDto category){
        return BaseResponse.ofSucceeded(categoryService.save(category));
    }

    @PutMapping("/{id}")
    public BaseResponse<CategoryResDto> update(@Validated @RequestBody CategoryDto category, @PathVariable String id){
        return BaseResponse.ofSucceeded(categoryService.update(category, id));
    }
}
