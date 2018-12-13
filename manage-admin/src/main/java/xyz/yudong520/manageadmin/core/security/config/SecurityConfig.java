package xyz.yudong520.manageadmin.core.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import xyz.yudong520.manageadmin.core.security.session.SessionStrategy;

/**
 * security的核心配置全部在这里集中
 * 继承用户名密码登陆认证的表单配置
 */
@Configuration
public class SecurityConfig extends FormLoginConfig {


    @Autowired
    private SessionStrategy sessionStrategy;

    //security核心方法，复写该方法
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //在配置中加入核心的用户名密码登陆
        applyPasswordAuthenticationConfig(http);
        http
                .headers().frameOptions().disable()
                .and()
                .authorizeRequests() //请求授权
                .antMatchers( "/login/auth","/login/page","/login/logout","/session/invalid",
                        "/css/**","/fonts/**","/images/**","/js/**","/json/**","/skin/**","/*.ico")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement()   //session管理
                .invalidSessionUrl("/session/invalid") //session失效的回跳地址
                .maximumSessions(1)  //最大链接数
                .expiredSessionStrategy(sessionStrategy) //session失效，过期处理
                .maxSessionsPreventsLogin(false)
                .and()
                .and()
//                .authorizeRequests() //请求授权
//                .antMatchers(
//                        "/login/auth",
//                        "/login/page"
//                )  //放过的url
//                .permitAll()    //上面配置的url全部都放过
//                .anyRequest()   //任何请求
//                .authenticated()  //要求授权
//                .and()
                .csrf().disable();//暂时关闭跨站请求伪造
    }


//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        //解决静态资源被拦截的问题
//        web.ignoring().antMatchers("/resources/**","/static/**","/css/**","/fonts/**","/images/**","/js/**","/json/**","/skin/**");
//    }


    //配置加密方式
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
