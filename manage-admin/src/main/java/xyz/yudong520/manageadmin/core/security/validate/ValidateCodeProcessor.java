package xyz.yudong520.manageadmin.core.security.validate;

import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.context.request.ServletWebRequest;
import xyz.yudong520.manageadmin.core.security.validate.exception.ValidateException;

import java.io.IOException;

/**
 * 校验验证码的模板方法
 */
public interface ValidateCodeProcessor {


    /**
     * 校验码存放的session的key值前缀
     */
    public static final String SESSION_VALIDATE_CODE="SESSION_VALIDATE_CODE_";

    //创建验证码
    void createValidateCode(ServletWebRequest request) throws IOException, ServletRequestBindingException;

    //校验验证码
    void validateCode(ServletWebRequest request) throws ValidateException;
}
