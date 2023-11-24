package vn.ptit.b19dccn576.BE.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vn.ptit.b19dccn576.BE.common.BaseResponse;
import vn.ptit.b19dccn576.BE.model.User;
import vn.ptit.b19dccn576.BE.service.IUserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping()
    public BaseResponse<List<User>> getAllUsers(){
        return BaseResponse.ofSucceeded(userService.getAllUsers());
    }

    @PostMapping()
    public BaseResponse<User> saveRole(@Validated @RequestBody User user){
        return BaseResponse.ofSucceeded(userService.save(user));
    }
}
