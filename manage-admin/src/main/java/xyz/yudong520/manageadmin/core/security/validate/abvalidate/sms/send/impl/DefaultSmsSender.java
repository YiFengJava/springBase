package xyz.yudong520.manageadmin.core.security.validate.abvalidate.sms.send.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.yudong520.manageadmin.core.security.validate.abvalidate.sms.send.SmsSender;

@Component
@Slf4j
public class DefaultSmsSender implements SmsSender {
    @Override
    public void sendSms(String mobile, String code) {
      log.info("向手机号为："+mobile+",发送验证码："+code);
    }
}
