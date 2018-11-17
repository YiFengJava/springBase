package spring.study.securityBrowser.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import spring.study.securitycore.authentication.FormLoginConfig;
import spring.study.securitycore.authentication.ValidateFilterConfig;
import spring.study.securitycore.authentication.handler.MyAuthenticationFailureHandler;
import spring.study.securitycore.authentication.handler.MyAuthenticationSuccessHandler;
import spring.study.securitycore.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import spring.study.securitycore.filter.ValidateCodeFilter;
import spring.study.securitycore.filter.ValidateFilter;
import spring.study.securitycore.filter.ValidateSmsCodeFilter;
import spring.study.securitycore.properties.SecurityConstants;
import spring.study.securitycore.properties.SecurityProperties;
import spring.study.securitycore.validate.ValidateCodeProcessorHolder;

import javax.sql.DataSource;

/**
 * 继承WebSecurityConfigurerAdapter
 * springSecurity提供的适配器类
 */
@Configuration
public class BrowserSecurityConfig
//        extends WebSecurityConfigurerAdapter
          extends FormLoginConfig
{

    //自定义的配置类
    @Autowired
    private SecurityProperties securityProperties;

    //成功处理
//    @Autowired
//    public MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    //失败的处理
//    @Autowired
//    public MyAuthenticationFailureHandler myAuthenticationFailureHandler;

    //配一个数据源
    @Autowired
    private DataSource dataSource;

    //用户查询服务
    @Autowired
    private UserDetailsService userDetailsService;

    //短信登陆配置
    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;


    @Autowired
    private ValidateCodeProcessorHolder  validateCodeProcessorHolder;


    @Autowired
    private ValidateFilterConfig validateFilterConfig;

    //配置加密方式
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
       //表单登陆
//        setMyAuthenticationFailureHandler(myAuthenticationFailureHandler);
//        setMyAuthenticationSuccessHandler(myAuthenticationSuccessHandler);
        applyPasswordAuthenticationConfig(http);
        http
                .apply(validateFilterConfig)
                .and()
                .apply(smsCodeAuthenticationSecurityConfig)
                .and()
                .rememberMe()        //开启记住我功能
                .tokenRepository(persistentTokenRepository())   //配置一个数据源来操作token  让用户与token对应并 储存在数据库
                .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())  //配置token的过期时间
                .userDetailsService(userDetailsService) //配置一个用户查询服务 ，使之根据token查到用户名 ，然后根据用户名查到用户信息
                .and()            //开始配置授权信息
                .authorizeRequests()   //对请求的授权
                .antMatchers(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL
                        , securityProperties.getBrowser().getLoginPage(),
                        SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX+"/*").permitAll() //该这些请求不需要身份验证
                .anyRequest()  //对任何请求
                .authenticated()  //都需要身份认证
                .and()      //配置 跨站请求伪造信息
                .csrf().disable();//暂时关闭跨站请求伪造
    }

    /**
     * 核心配置方法
     *
     * @param
     * @throws Exception
     */
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        //配置  图片校验过滤器
//        ValidateCodeFilter filter = new ValidateCodeFilter();
//        filter.setAuthenticationFailureHandler(myAuthenticationFailureHandler);
//        filter.setSecurityProperties(securityProperties);
//        filter.afterPropertiesSet();
//        //配置短信验证过滤器
//        ValidateSmsCodeFilter validateSmsCodeFilter = new ValidateSmsCodeFilter();
//        validateSmsCodeFilter.setAuthenticationFailureHandler(myAuthenticationFailureHandler);
//        validateSmsCodeFilter.setSecurityProperties(securityProperties);
//        validateSmsCodeFilter.afterPropertiesSet();
//
//        ValidateFilter validateFilter=new ValidateFilter();
//        validateFilter.setAuthenticationFailureHandler(myAuthenticationFailureHandler);
//        validateFilter.setSecurityProperties(securityProperties);
//        validateFilter.setValidateCodeProcessorHolder(validateCodeProcessorHolder);
//        validateFilter.afterPropertiesSet();
//
//        http
//                .addFilterBefore(validateFilter, UsernamePasswordAuthenticationFilter.class)
//                //校验与认证
//                //把短信校验过滤器加到过滤器链中 并在 UsernamePasswordAuthenticationFilter之前
////                .addFilterBefore(validateSmsCodeFilter, UsernamePasswordAuthenticationFilter.class)
//                //把图片校验过滤器加到过滤器链中  并在 UsernamePasswordAuthenticationFilter之前
////                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
//                .formLogin()   //表单登陆 身份认证的   过UsernamePasswordAuthenticationFilter之前
//                .loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL) //自定义登陆页面   跳转到登陆页面
//                .loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)  // 自定义的登录接口  会被UsernamePasswordAuthenticationFilter跳转到 /login 的post 请求上
//                .successHandler(myAuthenticationSuccessHandler)   //登陆成功时处理成功
//                .failureHandler(myAuthenticationFailureHandler)   //登陆失败时处理
//                .and()             //开始配置记住我功能
//                .rememberMe()        //开启记住我功能
//                .tokenRepository(persistentTokenRepository())   //配置一个数据源来操作token  让用户与token对应并 储存在数据库
//                .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())  //配置token的过期时间
//                .userDetailsService(userDetailsService) //配置一个用户查询服务 ，使之根据token查到用户名 ，然后根据用户名查到用户信息
//                .and()            //开始配置授权信息
//                .authorizeRequests()   //对请求的授权
//                .antMatchers(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL
//                        , securityProperties.getBrowser().getLoginPage(),
//                        SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX+"/*").permitAll() //该这些请求不需要身份验证
//                .anyRequest()  //对任何请求
//                .authenticated()  //都需要身份认证
//                .and()      //配置 跨站请求伪造信息
//                .csrf().disable()//暂时关闭跨站请求伪造
//                .apply(smsCodeAuthenticationSecurityConfig);  //把短信验证登陆配置到权限系统中
//    }


    //为记住我功能配置一个TokenRepository，并配置一个数据源来操作
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
//        tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }
}
