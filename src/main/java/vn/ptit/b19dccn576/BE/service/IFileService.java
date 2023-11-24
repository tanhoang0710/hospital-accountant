package vn.ptit.b19dccn576.BE.service;

import org.springframework.web.multipart.MultipartFile;

public interface IFileService {
    void importCategories(MultipartFile file);
}
