package vn.ptit.b19dccn576.BE.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class CategoryResDto {
    Long id;
    String name;
    Integer percentage;
    Date appliedDate;
    Date createdDate;
    Date lastModifiedDate;
}
