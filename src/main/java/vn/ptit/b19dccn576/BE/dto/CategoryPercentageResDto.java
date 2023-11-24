package vn.ptit.b19dccn576.BE.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@Builder
public class CategoryPercentageResDto {
    Long id;
    String name;
    Double totalTax;
}
