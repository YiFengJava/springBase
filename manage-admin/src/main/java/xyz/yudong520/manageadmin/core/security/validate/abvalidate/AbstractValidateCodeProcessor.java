package xyz.yudong520.manageadmin.core.security.validate.abvalidate;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import xyz.yudong520.manageadmin.core.security.validate.ValidateCodeProcessor;
import xyz.yudong520.manageadmin.core.security.validate.code.ValidateCode;
import xyz.yudong520.manageadmin.core.security.validate.code.ValidateType;
import xyz.yudong520.manageadmin.core.security.validate.exception.ValidateException;
import xyz.yudong520.manageadmin.core.security.validate.exception.ValidateExceptionEnum;
import xyz.yudong520.manageadmin.core.security.validate.generate.ValidateCodeGenerater;

import java.io.IOException;
import java.util.Map;

public  abstract class AbstractValidateCodeProcessor<C extends  ValidateCode> implements ValidateCodeProcessor {

    //操作session工具
    private SessionStrategy sessionStrategy=new HttpSessionSessionStrategy();

    @Autowired
    private Map<String,ValidateCodeGenerater> validateCodeGenerater;

    //core
    @Override
    public void createValidateCode(ServletWebRequest request) throws IOException, ServletRequestBindingException {
//        1,生成校验码
         C validateCode=generateCode(request);
//        2，保存校验码
        saveValidateCode(request,validateCode);
//        3，返回发送校验码
        sendValidateCode(request,validateCode);
    }


    /**
     * 得到校验码类型
     * @return
     */
    private ValidateType getValidateType(){
        //得到当前调用对象的class类的简单名称  以为 名称是以 校验类型（sms.image）+ValidateCodeProcessor组成
        String simpleName = getClass().getSimpleName();
        //分割类名得到类型
        String validateCodeProcessor = StringUtils.substringBefore(simpleName, "ValidateCodeProcessor");
        //全部转换成大写
        String s = validateCodeProcessor.toUpperCase();
        //组装成定义好的校验类型的枚举
        return ValidateType.valueOf(s);
    }


    /**
     * 生成校验码
     * @param request
     * @return
     */
    protected C generateCode(ServletWebRequest request) {
        //得到类型
        ValidateType validateType = getValidateType();
        String type = validateType.toString().toLowerCase();
        //拼接得到校验码生成器的匹配器中得到该类型的校验码生成器
        String key =type+ValidateCodeGenerater.class.getSimpleName();
        //得到校验码
        ValidateCode validateCode = validateCodeGenerater.get(key).generateValidateCode(request.getRequest());
        return (C)validateCode;
    }

    /**
     * 发送验证码
     * @param request
     * @param validateCode
     * @throws IOException
     * @throws ServletRequestBindingException
     */
    protected abstract void sendValidateCode(ServletWebRequest request, C validateCode) throws IOException, ServletRequestBindingException;


    /**
     * 得到放在session中的校验码的key值
     * @return
     */
    private String getSessionKey(){
        ValidateType validateType = getValidateType();
        String key=SESSION_VALIDATE_CODE+validateType.toString().toUpperCase();
        return key;
    }

    /**
     * 保存校验到session
     * @param request
     * @param validateCode
     */
    private void saveValidateCode(ServletWebRequest request, C validateCode) {
        String key = getSessionKey();
        sessionStrategy.setAttribute(request,key
                ,new ValidateCode(validateCode.getCode(),validateCode.getExpireTime()));
    }


    /**
     * 校验验证码
     * @param request
     */
    @Override
    public void validateCode(ServletWebRequest request) throws ValidateException {
        //得到的校验码的session的key
        String key = getSessionKey();
        //出session得到校验码对象
        ValidateCode attribute = (ValidateCode) sessionStrategy.getAttribute(request, key);
        //开始校验
        if(attribute==null){
            //校验码不存在
            throw  new ValidateException(ValidateExceptionEnum.VALIDATE_NOT_EXIST);
        }
        //得到校验类型
        ValidateType validateType = getValidateType();
        //得到校验码的表单提交过来的参数名称
        String paramNameOnvalidete = validateType.getParamNameOnvalidete();
        //得到session中的校验码的码值
        String code = attribute.getCode();
        String stringParameter ="";
        try {
            stringParameter =ServletRequestUtils.getStringParameter(request.getRequest(), paramNameOnvalidete);
        } catch (ServletRequestBindingException e) {
            throw  new ValidateException(ValidateExceptionEnum.GET_VALIDATE_CODE_ERR);
        }
        if(StringUtils.isBlank(stringParameter)){   //校验码不能为空
            throw  new ValidateException(ValidateExceptionEnum.VALIDATE_NOT_EMPT);
        }
        if(StringUtils.isBlank(code)){   //校验码不存在
            throw  new ValidateException(ValidateExceptionEnum.VALIDATE_NOT_EXIST);
        }
        if(attribute.isExpireTime()){  //校验码已过期
            throw  new ValidateException(ValidateExceptionEnum.VALIDATE_IS_EXPIRE_EMPT);
        }
        if(!StringUtils.equals(stringParameter,code)){//校验码不匹配
            throw  new ValidateException(ValidateExceptionEnum.VALIDATE_CODE_NOT_MATCH);
        }
        //从session中删除校验码
        sessionStrategy.removeAttribute(request,key);
    }
   

    
}
