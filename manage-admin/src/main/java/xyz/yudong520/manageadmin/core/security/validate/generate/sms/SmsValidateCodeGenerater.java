package xyz.yudong520.manageadmin.core.security.validate.generate.sms;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import xyz.yudong520.manageadmin.core.security.properties.SecurityCommon;
import xyz.yudong520.manageadmin.core.security.validate.code.ValidateCode;
import xyz.yudong520.manageadmin.core.security.validate.code.sms.SmsValidateCode;
import xyz.yudong520.manageadmin.core.security.validate.generate.ValidateCodeGenerater;

import javax.servlet.http.HttpServletRequest;

@Component
public class SmsValidateCodeGenerater implements ValidateCodeGenerater {

    @Autowired
    private SecurityCommon securityCommon;

    @Override
    public SmsValidateCode generateValidateCode(HttpServletRequest request) {
        int length = securityCommon.getValidateCode().getSmsCode().getLength();
        int expireIn = securityCommon.getValidateCode().getSmsCode().getExpireIn();
        String random = RandomStringUtils.randomNumeric(length);
        return new SmsValidateCode(random,expireIn);
    }
}
