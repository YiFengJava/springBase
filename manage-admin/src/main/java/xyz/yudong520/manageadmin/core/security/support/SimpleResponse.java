package xyz.yudong520.manageadmin.core.security.support;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SimpleResponse {
    private Integer status;
    private String message;
    private Object data;
}
