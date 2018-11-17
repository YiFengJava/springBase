package spring.study.securitycore.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import spring.study.securitycore.authentication.handler.MyAuthenticationFailureHandler;
import spring.study.securitycore.authentication.handler.MyAuthenticationSuccessHandler;
import spring.study.securitycore.filter.ValidateFilter;
import spring.study.securitycore.properties.SecurityProperties;
import spring.study.securitycore.validate.ValidateCodeProcessorHolder;

@Component
public class ValidateFilterConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    //成功处理
    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    //失败的处理
    @Autowired
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;


    @Autowired
    private ValidateCodeProcessorHolder validateCodeProcessorHolder;

    //自定义的配置类
    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        ValidateFilter validateFilter=new ValidateFilter();
        validateFilter.setAuthenticationFailureHandler(myAuthenticationFailureHandler);
        validateFilter.setSecurityProperties(securityProperties);
        validateFilter.setValidateCodeProcessorHolder(validateCodeProcessorHolder);
        validateFilter.afterPropertiesSet();
        http
                .addFilterBefore(validateFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
