package spring.study.securitycore.validate.sms;

public class DefaultSmsCodeSender implements SmsCodeSender {
    @Override
    public void send(String mobile, String code) {
        System.out.println("向手机号为:"+mobile+"发送验证码"+code);
    }
}
