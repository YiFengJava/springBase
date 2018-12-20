package xyz.yudong520.manageadmin.core.security.validate.abvalidate.sms.send;

public interface SmsSender {

    void sendSms(String mobile,String code);
}

