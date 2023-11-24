package vn.ptit.b19dccn576.BE.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vn.ptit.b19dccn576.BE.common.BaseResponse;
import vn.ptit.b19dccn576.BE.dto.RoleDto;
import vn.ptit.b19dccn576.BE.model.Role;
import vn.ptit.b19dccn576.BE.service.IRoleService;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @GetMapping()
    public BaseResponse<List<Role>> getAllRoles(){
        return BaseResponse.ofSucceeded(roleService.getAllRoles());

        // bảng danh mục thiết bị y tế phải chịu thuế(id, tên, ngày áp dụng, mức thuế phải chịu)
        // bảng danh mục thuốc phải chịu thuế(id, tên, ngày áp dụng, mức thuế phải chịu)
        // bảng thuốc nhập(id, tên, loại, ai nhập, giá, số lượng, nhà phân phối, ngày nhập, nhóm)
        // bảng thiết bị y tế nhập(id, tên, loại, ai nhập, giá, số lượng, nhà phân phối, ngày nhập, nhóm)
        // bảng cấu hình % thuế suất(id, loại, %, ngày áp dụng, ai áp dụng)
        // role(id, tên)
        // user(id, tên, email, sđt, role)
    }

    @PostMapping()
    public BaseResponse<Role> saveRole(@Validated @RequestBody Role role){
        return BaseResponse.ofSucceeded(roleService.save(role));
    }
}
