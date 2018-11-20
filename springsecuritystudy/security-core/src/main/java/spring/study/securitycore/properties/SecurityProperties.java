package spring.study.securitycore.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 自定义的配置类
 */
@ConfigurationProperties(prefix = "mysecurity")
@Data
public class SecurityProperties {

    //浏览器配置
    private SecurityBrowserProperties browser =new SecurityBrowserProperties();

    //校验码配置
    private ValidateCodeProperties validate=new ValidateCodeProperties();

    private  SocialProperties socialProperties=new SocialProperties();


}
