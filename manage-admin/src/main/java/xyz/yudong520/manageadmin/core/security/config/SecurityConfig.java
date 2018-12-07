package xyz.yudong520.manageadmin.core.security.config;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * security的核心配置全部在这里集中
 * 继承用户名密码登陆认证的表单配置
 */
@Configuration
public class SecurityConfig extends FormLoginConfig {


    //security核心方法，复写该方法
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //在配置中加入核心的用户名密码登陆
        applyPasswordAuthenticationConfig(http);
        http
                .authorizeRequests() //请求授权
                .antMatchers( "/login/auth","/login/page","/login/success","/login/logout",
                        "/css/**","/font/**","/images/**","/js/**","/json/**","/skin/**")
                .permitAll()
                .anyRequest().authenticated()
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
//        web.ignoring().antMatchers("/static/**","/css/**","/font/**","/images/**","/js/**","/json/**","/skin/**");
//    }


    //配置加密方式
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode("123456");
        System.out.print(encode);
    }


}
