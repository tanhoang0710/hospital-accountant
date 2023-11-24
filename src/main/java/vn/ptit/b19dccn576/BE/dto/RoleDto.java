package vn.ptit.b19dccn576.BE.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class RoleDto {
    @NonNull
    String name;
}
