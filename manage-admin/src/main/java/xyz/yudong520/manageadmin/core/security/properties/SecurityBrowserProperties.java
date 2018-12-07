package xyz.yudong520.manageadmin.core.security.properties;

import lombok.Data;

/**
 * 浏览器登陆配置
 */
@Data
public class SecurityBrowserProperties {

    //配置登陆页  默认标准登陆页
    private String loginPage ="/baseLogin.html";

    //配置登陆类型 默认Json
    private LoginType loginType=LoginType.JSON;

    private String signUpUrl = "/signup.html";

    //配置记住我的过期时间
    private int rememberMeSeconds = 3600;

}
