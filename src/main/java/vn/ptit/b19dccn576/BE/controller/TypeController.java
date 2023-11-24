package vn.ptit.b19dccn576.BE.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vn.ptit.b19dccn576.BE.common.BaseResponse;
import vn.ptit.b19dccn576.BE.model.Type;
import vn.ptit.b19dccn576.BE.service.ITypeService;

import java.util.List;

@RestController
@RequestMapping("/types")
public class TypeController {

    @Autowired
    private ITypeService typeService;

    @GetMapping()
    public BaseResponse<List<Type>> getAllUsers(){
        return BaseResponse.ofSucceeded(typeService.getAllTypes());
    }

    @PostMapping()
    public BaseResponse<Type> saveRole(@Validated @RequestBody Type type){
        return BaseResponse.ofSucceeded(typeService.save(type));
    }
}
