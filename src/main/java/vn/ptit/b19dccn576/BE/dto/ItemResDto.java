package vn.ptit.b19dccn576.BE.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class ItemResDto {
    Long id;
    String name;
    Double price;
    Integer quantity;
    String merchandise;
    String categoryName;
    String userName;
    Date createdDate;
    Date lastModifiedDate;
}
