package xyz.yudong520.manageadmin.core.security.validate.code.sms;

import xyz.yudong520.manageadmin.core.security.validate.code.ValidateCode;

import java.time.LocalDateTime;

public class SmsValidateCode  extends ValidateCode {


    public SmsValidateCode() {
        super();
    }

    public SmsValidateCode(String code) {
        super(code);
    }

    public SmsValidateCode(String code, LocalDateTime expireTime) {
        super(code, expireTime);
    }

    public SmsValidateCode(String code, long expire) {
        super(code, expire);
    }
}
