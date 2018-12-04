package xyz.yudong520.manageadmin.core.security.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 这是核心的所有的登陆方式都会有一个表单用户名密码登陆
 * 表单登陆的securuty的配置
 */
public class FormLoginConfig extends WebSecurityConfigurerAdapter {

    /**
     * 加入用户名密码登陆的配置
     * @param http
     * @throws Exception
     */
    protected void applyPasswordAuthenticationConfig(HttpSecurity http) throws Exception {
        http
                .formLogin()  //表单登陆 登陆认证 在UsernamePasswordAuthenticationFilter之前
                .loginPage("/login")  //自定义的登陆页面
                .failureForwardUrl("/login/auth");   //自定的需要认证的接口
    }
}
