package spring.study.securitycore.properties;

import lombok.Data;

/**
 * 短信校验码配置
 */
@Data
public class SmsCodeProperties {
    //校验的配置
    private int length=6;
    //过期时间
    private int expireIn=60;
    //需校验的url
    private String url;

}
