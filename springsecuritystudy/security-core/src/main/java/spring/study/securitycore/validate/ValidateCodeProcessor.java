package spring.study.securitycore.validate;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 验证码生成接口
 */
public interface ValidateCodeProcessor {

    //存放在session中的校验码的前缀
    String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";

    //验证码生产的方法
    void createCode(ServletWebRequest request) throws Exception;

    void validate(ServletWebRequest request);
}
