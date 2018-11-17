package spring.study.securitycore.authentication;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import spring.study.securitycore.authentication.handler.MyAuthenticationFailureHandler;
import spring.study.securitycore.authentication.handler.MyAuthenticationSuccessHandler;
import spring.study.securitycore.properties.SecurityConstants;
@Data
public class FormLoginConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    //成功处理
     MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Autowired
    //失败的处理
     MyAuthenticationFailureHandler myAuthenticationFailureHandler;


    protected void applyPasswordAuthenticationConfig(HttpSecurity http) throws  Exception{
            http
                    .formLogin()   //表单登陆 身份认证的   过UsernamePasswordAuthenticationFilter之前
                    .loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL) //自定义登陆页面   跳转到登陆页面
                    .loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)  // 自定义的登录接口  会被UsernamePasswordAuthenticationFilter跳转到 /login 的post 请求上
                    .successHandler(myAuthenticationSuccessHandler)   //登陆成功时处理成功
                    .failureHandler(myAuthenticationFailureHandler);   //登陆失败时处理
    }
}
