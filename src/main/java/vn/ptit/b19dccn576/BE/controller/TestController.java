package vn.ptit.b19dccn576.BE.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.ptit.b19dccn576.BE.common.BaseResponse;

@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping()
    public BaseResponse<String> getAllEmployees(){
        return BaseResponse.ofSucceeded("Hello world");

        // bảng danh mục thiết bị y tế phải chịu thuế(id, tên, ngày áp dụng, mức thuế phải chịu)
        // bảng danh mục thuốc phải chịu thuế(id, tên, ngày áp dụng, mức thuế phải chịu)
        // bảng thuốc nhập(id, tên, loại, ai nhập, giá, số lượng, nhà phân phối, ngày nhập, nhóm)
        // bảng thiết bị y tế nhập(id, tên, loại, ai nhập, giá, số lượng, nhà phân phối, ngày nhập, nhóm)
        // bảng cấu hình % thuế suất(id, loại, %, ngày áp dụng, ai áp dụng)
        // role(id, tên)
        // user(id, tên, email, sđt, role)
    }
}
