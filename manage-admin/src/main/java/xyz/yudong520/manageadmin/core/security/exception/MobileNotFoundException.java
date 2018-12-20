package xyz.yudong520.manageadmin.core.security.exception;

import org.springframework.security.core.AuthenticationException;

public class MobileNotFoundException   extends AuthenticationException {
    public MobileNotFoundException(String msg, Throwable t) {
        super("不存在该电话号码的用户", t);
    }
    public MobileNotFoundException(String msg) {
        super("不存在该电话号码的用户");
    }
}
