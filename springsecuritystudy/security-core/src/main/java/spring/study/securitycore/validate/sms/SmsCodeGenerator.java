package spring.study.securitycore.validate.sms;

import lombok.Data;
import org.apache.commons.lang.RandomStringUtils;
import spring.study.securitycore.properties.SecurityProperties;
import spring.study.securitycore.validate.ValidateCodeGenerator;
import spring.study.securitycore.validate.image.ImageCode;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

@Data
public class SmsCodeGenerator implements ValidateCodeGenerator {


    private SecurityProperties securityProperties;


    @Override
    public ValidateCode createValidateCode(HttpServletRequest request) {
        int count=securityProperties.getValidate().getSmsCode().getLength();
        int expireIn = securityProperties.getValidate().getSmsCode().getExpireIn();
        String code= RandomStringUtils.randomNumeric(count);
        ValidateCode validateCode = new ValidateCode(code,expireIn);
        return  validateCode;
    }
}
