package spring.study.securitycore.validate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.study.securitycore.properties.SecurityProperties;
import spring.study.securitycore.validate.image.ImageCodeGenerator;
import spring.study.securitycore.validate.sms.DefaultSmsCodeSender;
import spring.study.securitycore.validate.sms.SmsCodeGenerator;
import spring.study.securitycore.validate.sms.SmsCodeSender;

@Configuration
public class ValidateCodeBeanConfig {

    @Autowired
    private SecurityProperties securityProperties;

    @Bean
    @ConditionalOnMissingBean(name="imageCodeGenerator")
    public ValidateCodeGenerator imageCodeGenerator(){
        ImageCodeGenerator imageCodeGenerator = new ImageCodeGenerator();
        imageCodeGenerator.setSecurityProperties(securityProperties);
        return imageCodeGenerator;
    }

    @Bean
    @ConditionalOnMissingBean(name="smsCodeGenerator")
    public ValidateCodeGenerator smsCodeGenerator(){
        SmsCodeGenerator smsCodeGenerator = new SmsCodeGenerator();
        smsCodeGenerator.setSecurityProperties(securityProperties);
        return smsCodeGenerator;
    }

    @Bean
    @ConditionalOnMissingBean(SmsCodeSender.class)
    public SmsCodeSender  smsCodeSender(){
        return  new DefaultSmsCodeSender();
    }
}
