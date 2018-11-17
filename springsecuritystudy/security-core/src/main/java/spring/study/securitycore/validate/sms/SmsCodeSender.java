package spring.study.securitycore.validate.sms;

public interface SmsCodeSender {
    void send(String mobile,String code);
}
