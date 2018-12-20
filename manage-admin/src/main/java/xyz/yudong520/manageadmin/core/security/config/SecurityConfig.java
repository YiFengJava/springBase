package xyz.yudong520.manageadmin.core.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.social.security.SpringSocialConfigurer;
import xyz.yudong520.manageadmin.core.security.service.SecurityService;
import xyz.yudong520.manageadmin.core.security.session.SessionStrategy;
import xyz.yudong520.manageadmin.core.security.validate.mobile.SmsLoginConfig;

import javax.sql.DataSource;

/**
 * security的核心配置全部在这里集中
 * 继承用户名密码登陆认证的表单配置
 */
@Configuration
public class SecurityConfig extends FormLoginConfig {


    @Autowired
    private SessionStrategy sessionStrategy;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private  ValidateFilterConfig validateFilterConfig;

    //配一个数据源
    @Autowired
    private DataSource dataSource;

    @Autowired
    public SpringSocialConfigurer springSocialConfigurer;


    @Autowired
    private SmsLoginConfig smsLoginConfig;

    //security核心方法，复写该方法
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //在配置中加入核心的用户名密码登陆
        applyPasswordAuthenticationConfig(http);
        http
                .apply(smsLoginConfig)  //加上短信验证码登陆
                .and()
                .apply(validateFilterConfig)  //加上校验验证码
                .and()
                .apply(springSocialConfigurer)
                .and()
                .headers().frameOptions().disable()  //用iframe
                .and()
                .authorizeRequests() //请求授权
                .antMatchers( "/login/auth","/login/page","/login/logout","/session/invalid",
                        "/code/*",
                        "auth/qq",
                        "/login/regist",
                        "/signin",
                        "/login/smsLogin",
                        "/css/**","/fonts/**","/images/**","/js/**","/json/**","/skin/**","/*.ico")
                .permitAll()       //上面的匹配都允许通过
                .anyRequest().authenticated()   //任何请求都需要认证
                .and()
                .sessionManagement()   //session管理
                .invalidSessionUrl("/session/invalid") //session失效的回跳地址
                .maximumSessions(1)  //最大链接数
                .expiredSessionStrategy(sessionStrategy) //session失效，过期处理
                .maxSessionsPreventsLogin(false)
                .and()
                .and()
                .rememberMe()        //开启记住我功能
                .tokenRepository(persistentTokenRepository())   //配置一个数据源来操作token  让用户与token对应并 储存在数据库
                .tokenValiditySeconds(180)  //配置token的过期时间
                .userDetailsService(securityService) //配置一个用户查询服务 ，使之根据token查到用户名 ，然后根据用户名查到用户信息
                .and()            //开始配置授权信息
                .csrf().disable();//暂时关闭跨站请求伪造
    }


    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
//        tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }
    //配置加密方式
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
