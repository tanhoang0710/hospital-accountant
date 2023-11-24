package vn.ptit.b19dccn576.BE.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class CategoryDto {
    String name;
    Integer percentage;
    Date appliedDate;
    Long typeId;
}
