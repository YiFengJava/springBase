package xyz.yudong520.manageadmin.core.security.properties;

import lombok.Data;

@Data
public class ValidateCode {
    private ImageCode imageCode;
    private SmsCode smsCode;
}
