package xyz.yudong520.manageadmin.core.security.validate.abvalidate.sms;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import xyz.yudong520.manageadmin.core.security.properties.Commom;
import xyz.yudong520.manageadmin.core.security.validate.abvalidate.AbstractValidateCodeProcessor;
import xyz.yudong520.manageadmin.core.security.validate.abvalidate.sms.send.SmsSender;
import xyz.yudong520.manageadmin.core.security.validate.code.sms.SmsValidateCode;

import java.io.IOException;
@Component
@Data
public class SmsValidateCodeProcessor extends AbstractValidateCodeProcessor<SmsValidateCode> {

    @Autowired
    private SmsSender smsSender;

    @Override
    protected void sendValidateCode(ServletWebRequest request, SmsValidateCode validateCode) throws IOException, ServletRequestBindingException {
        String parameterName = Commom.LOGIN_MOBILE_PARAMETER_NAME;
        String mobile = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), parameterName);
        String code = validateCode.getCode();
        smsSender.sendSms(mobile,code);
    }
}
