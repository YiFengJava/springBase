package spring.study.securitycore.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 自定义的配置类
 */
@ConfigurationProperties(prefix = "mysecurity")
public class SecurityProperties {

    //浏览器配置
    private SecurityBrowserProperties browser =new SecurityBrowserProperties();

    //校验码配置
    private ValidateCodeProperties validate=new ValidateCodeProperties();

    public ValidateCodeProperties getValidate() {
        return validate;
    }

    public void setValidate(ValidateCodeProperties validate) {
        this.validate = validate;
    }

    public SecurityBrowserProperties getBrowser() {
        return browser;
    }

    public void setBrowser(SecurityBrowserProperties browser) {
        this.browser = browser;
    }
}
