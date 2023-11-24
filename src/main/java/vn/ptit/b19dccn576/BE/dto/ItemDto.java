package vn.ptit.b19dccn576.BE.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ItemDto {
    String name;
    Double price;
    Integer quantity;
    String merchandise;
    Long categoryId;
    Long userId;
}
