package xyz.yudong520.manageadmin.core.security.validate.exception;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

public class ValidateException extends AuthenticationException {

    private Integer code;

    private String message;

    public ValidateException(String msg, Throwable t, Integer code, String message) {
        super(msg, t);
        this.code = code;
        this.message = message;
    }

    public ValidateException(String msg, Integer code, String message) {
        super(msg);
        this.code = code;
        this.message = message;
    }

    public ValidateException(ValidateExceptionEnum exceptionEnum) {
        super(exceptionEnum.getMessage());
        this.code = exceptionEnum.getCode();
        this.message = exceptionEnum.getMessage();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
