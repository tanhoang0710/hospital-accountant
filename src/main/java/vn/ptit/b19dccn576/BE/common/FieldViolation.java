package vn.ptit.b19dccn576.BE.common;

import lombok.*;
import lombok.experimental.Accessors;

@Getter
@Setter
@RequiredArgsConstructor
@Accessors(chain = true)
@AllArgsConstructor
public class FieldViolation {

    private String field;
    private String description;
}
