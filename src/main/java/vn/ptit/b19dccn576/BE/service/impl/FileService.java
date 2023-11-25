package vn.ptit.b19dccn576.BE.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vn.ptit.b19dccn576.BE.helper.ExcelHelper;
import vn.ptit.b19dccn576.BE.service.ICategoryService;
import vn.ptit.b19dccn576.BE.service.IFileService;

import java.io.IOException;

@Service
public class FileService implements IFileService {
    private ICategoryService categoryService;

    public FileService(ICategoryService categoryService){
        this.categoryService = categoryService;
    }

    @Override
    public void importCategories(MultipartFile file) {
        try {
            var categories = ExcelHelper.excelToCategories(file.getInputStream());
            categoryService.save(categories);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
