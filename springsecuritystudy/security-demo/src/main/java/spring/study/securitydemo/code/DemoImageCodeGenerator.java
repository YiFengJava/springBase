package spring.study.securitydemo.code;

import spring.study.securitycore.validate.ValidateCodeGenerator;
import spring.study.securitycore.validate.image.ImageCode;
import spring.study.securitycore.validate.sms.ValidateCode;

import javax.servlet.http.HttpServletRequest;

//@Component("imageCodeGenerator")
public class DemoImageCodeGenerator implements ValidateCodeGenerator {

    @Override
    public ValidateCode createValidateCode(HttpServletRequest request) {
        System.out.println("高级的图形生产代码");
        return null;
    }
}
