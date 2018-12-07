package xyz.yudong520.manageadmin.core.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import xyz.yudong520.manageadmin.core.security.handler.MyAuthenticationFailureHandler;
import xyz.yudong520.manageadmin.core.security.handler.MyAuthenticationSuccessHandler;

/**
 * 这是核心的所有的登陆方式都会有一个表单用户名密码登陆
 * 表单登陆的securuty的配置
 */
public class FormLoginConfig
        extends WebSecurityConfigurerAdapter
{

    @Autowired
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;
//
    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
    /**
     * 加入用户名密码登陆的配置
     * @param http
     * @throws Exception
     */
    protected void applyPasswordAuthenticationConfig(HttpSecurity http) throws Exception {
        http
                .formLogin()  //表单登陆 登陆认证 在UsernamePasswordAuthenticationFilter之前
                .loginPage("/login/page")  //自定义的登陆页面
                .loginProcessingUrl("/login/auth")  //自定的需要认证的接口
                .successForwardUrl("/login/success")
                .successHandler(myAuthenticationSuccessHandler)
                .failureHandler(myAuthenticationFailureHandler)
//                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/login/logout");
//                .permitAll();
    }
}
