package spring.study.securitycore.validate;
import spring.study.securitycore.validate.sms.ValidateCode;

import javax.servlet.http.HttpServletRequest;

public interface ValidateCodeGenerator {

    ValidateCode createValidateCode(HttpServletRequest request);
}
