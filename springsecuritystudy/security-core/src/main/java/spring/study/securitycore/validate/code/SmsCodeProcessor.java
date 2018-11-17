package spring.study.securitycore.validate.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import spring.study.securitycore.properties.SecurityConstants;
import spring.study.securitycore.validate.sms.SmsCodeSender;
import spring.study.securitycore.validate.sms.ValidateCode;
@Component

public class SmsCodeProcessor extends  AbstractValidateCodeProcessor<ValidateCode> {

    @Autowired
    private SmsCodeSender smsCodeSender;

    @Override
    protected void sendCode(ServletWebRequest request, ValidateCode validateCode) throws Exception {
        String paramName = SecurityConstants.DEFAULT_PARAMETER_NAME_MOBILE;
        String mobile = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), paramName);
        smsCodeSender.send(mobile,validateCode.getCode());
    }
}
