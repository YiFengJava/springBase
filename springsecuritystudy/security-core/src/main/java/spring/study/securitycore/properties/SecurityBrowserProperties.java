package spring.study.securitycore.properties;

/**
 * 浏览器登陆配置
 */
public class SecurityBrowserProperties {

    //配置登陆页  默认标准登陆页
    private String loginPage ="/baseLogin.html";

    //配置登陆类型 默认Json
    private LoginType loginType=LoginType.JSON;

    //配置记住我的过期时间
    private int rememberMeSeconds = 3600;

    public int getRememberMeSeconds() {
        return rememberMeSeconds;
    }

    public void setRememberMeSeconds(int rememberMeSeconds) {
        this.rememberMeSeconds = rememberMeSeconds;
    }

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }
}
