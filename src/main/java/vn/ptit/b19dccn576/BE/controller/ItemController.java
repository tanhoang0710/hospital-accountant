package vn.ptit.b19dccn576.BE.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vn.ptit.b19dccn576.BE.common.BaseResponse;
import vn.ptit.b19dccn576.BE.dto.ItemDto;
import vn.ptit.b19dccn576.BE.dto.ItemResDto;
import vn.ptit.b19dccn576.BE.model.Item;
import vn.ptit.b19dccn576.BE.service.IItemService;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private IItemService itemService;

    @GetMapping()
    public BaseResponse<List<Item>> getAllUsers(){
        return BaseResponse.ofSucceeded(itemService.getAllItems());
    }

    @PostMapping()
    public BaseResponse<Item> saveRole(@Validated @RequestBody ItemDto item){
        return BaseResponse.ofSucceeded(itemService.save(item));
    }

    @PutMapping("/{id}")
    public BaseResponse<ItemResDto> update(@Validated @RequestBody ItemDto item, @PathVariable String id){
        return BaseResponse.ofSucceeded(itemService.update(item, id));
    }
}
