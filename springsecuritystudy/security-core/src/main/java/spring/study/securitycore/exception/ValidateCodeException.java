package spring.study.securitycore.exception;

import org.springframework.security.core.AuthenticationException;

public class ValidateCodeException  extends AuthenticationException{
    //security定义的一个抽象的异常基类  所有身份认证异常中的基类


    public ValidateCodeException(String msg) {
        super(msg);
    }
}
