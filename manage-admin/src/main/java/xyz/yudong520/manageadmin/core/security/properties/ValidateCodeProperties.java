package xyz.yudong520.manageadmin.core.security.properties;

import lombok.Data;

/**
 * 校验码
 */
@Data
public class ValidateCodeProperties {

    //图片配置
    private ImageCodeProperties image=new ImageCodeProperties();

    //短信配置
    private SmsCodeProperties smsCode=new SmsCodeProperties();

}
