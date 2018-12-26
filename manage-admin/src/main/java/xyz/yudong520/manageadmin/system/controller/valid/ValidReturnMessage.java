package xyz.yudong520.manageadmin.system.controller.valid;

import lombok.Data;

@Data
public class ValidReturnMessage {
    private String fieldName;
    private String message;

    public ValidReturnMessage(String fieldName, String message) {
        this.fieldName = fieldName;
        this.message = message;
    }
}
