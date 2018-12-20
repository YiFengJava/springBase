package xyz.yudong520.manageadmin.core.security.validate.generate;

import org.springframework.web.context.request.ServletWebRequest;
import xyz.yudong520.manageadmin.core.security.validate.code.ValidateCode;

import javax.servlet.http.HttpServletRequest;

public interface ValidateCodeGenerater {

    ValidateCode generateValidateCode(HttpServletRequest request);
}
