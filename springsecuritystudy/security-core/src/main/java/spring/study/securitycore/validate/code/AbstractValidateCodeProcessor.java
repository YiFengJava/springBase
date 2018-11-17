package spring.study.securitycore.validate.code;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import spring.study.securitycore.exception.ValidateCodeException;
import spring.study.securitycore.validate.ValidateCodeGenerator;
import spring.study.securitycore.validate.ValidateCodeProcessor;
import spring.study.securitycore.validate.sms.ValidateCode;

import java.util.Map;

/**
 * 该抽象方法是为校验码的生成
 * @param <C>
 */
public abstract class AbstractValidateCodeProcessor<C extends ValidateCode> implements ValidateCodeProcessor{

    //操作session工具
    private SessionStrategy sessionStrategy=new HttpSessionSessionStrategy();

    //把所有的ValidateCodeGenerator类型的bean注入 名字为key
    @Autowired
    private Map<String, ValidateCodeGenerator> validateCodeGenerators;


    /**
     * 创建校验码的实现
     * @param request
     * @throws Exception
     */
    @Override
    public void createCode(ServletWebRequest request) throws Exception {
        //1，生产校验码
        C validateCode=generatorCode(request);
        // 2，保存校验码
        saveCode(request,validateCode);
        // 3发送校验码
        sendCode(request,validateCode);
    }

    @Override
    public void validate(ServletWebRequest request) {
        ValidateCodeType validateCodeType = getValidateCodeType(request);
        String sessionKey = getSessionKey(request);
        C validateCode = (C) sessionStrategy.getAttribute(request, sessionKey);
        if(validateCode==null){
            throw  new ValidateCodeException(validateCodeType + "验证码的值不能为空");
        }
        String code = validateCode.getCode();
        String stringParameter;
        try {
            stringParameter = ServletRequestUtils.getStringParameter(request.getRequest(), validateCodeType.getParamNameOnValidate());
        } catch (ServletRequestBindingException e) {
            throw  new ValidateCodeException("获取验证码的值失败");
        }
        if (StringUtils.isBlank(stringParameter)) {
            throw new ValidateCodeException(validateCodeType + "验证码的值不能为空");
        }
        if (validateCode == null) {
            throw new ValidateCodeException(validateCodeType + "验证码不存在");
        }
        if (validateCode.isExpried()) {
            sessionStrategy.removeAttribute(request, sessionKey);
            throw new ValidateCodeException(validateCodeType + "验证码已过期");
        }

        if (!StringUtils.equals(validateCode.getCode(), stringParameter)) {
            throw new ValidateCodeException(validateCodeType + "验证码不匹配");
        }
        sessionStrategy.removeAttribute(request, sessionKey);
    }

    //发送验证码的抽象方法
    protected abstract void sendCode(ServletWebRequest request, C validateCode) throws  Exception;

    //把生成的验证码保存在session中
    protected void saveCode(ServletWebRequest request, C validateCode){
        sessionStrategy.setAttribute(request,getSessionKey(request),new ValidateCode(validateCode.getCode(),validateCode.getExpireTime()));
    }

    //得到验证码保存在session中的key
    private String getSessionKey(ServletWebRequest request) {
        return SESSION_KEY_PREFIX + getValidateCodeType(request).toString().toUpperCase();
    }

    //得到验证码的类型
    private ValidateCodeType getValidateCodeType(ServletWebRequest request) {
        String type = StringUtils.substringBefore(getClass().getSimpleName(), "CodeProcessor");
        return ValidateCodeType.valueOf(type.toUpperCase());
    }

    //生成验证码
    protected  C generatorCode(ServletWebRequest request){
        String type = getValidateCodeType(request).toString().toLowerCase();
        String generatorName = type + "CodeGenerator";
        ValidateCodeGenerator validateCodeGenerator=validateCodeGenerators.get(generatorName);
        if (validateCodeGenerator == null) {
            throw new ValidateCodeException("验证码生成器" + generatorName + "不存在");
        }
        return  (C) validateCodeGenerator.createValidateCode(request.getRequest());
    }
}
