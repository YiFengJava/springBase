package xyz.yudong520.manageadmin.core.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import xyz.yudong520.manageadmin.core.security.handler.MyAuthenticationFailureHandler;
import xyz.yudong520.manageadmin.core.security.properties.SecurityCommon;
import xyz.yudong520.manageadmin.core.security.validate.ValidateCodeProcessor;
import xyz.yudong520.manageadmin.core.security.validate.filter.ValidateCodeFiler;
import xyz.yudong520.manageadmin.core.security.validate.mobile.SmsLoginConfig;

import java.util.Map;

@Component
public class ValidateFilterConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain,HttpSecurity> {


    @Autowired
    private SecurityCommon securityCommon;

    @Autowired
    private Map<String,ValidateCodeProcessor> validateCodeProcessorMap;

    @Autowired
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;



    @Override
    public void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        ValidateCodeFiler validateCodeFiler = new ValidateCodeFiler();
        validateCodeFiler.setSecurityCommon(securityCommon);
        validateCodeFiler.setValidateCodeProcessorMap(validateCodeProcessorMap);
        validateCodeFiler.setMyAuthenticationFailureHandler(myAuthenticationFailureHandler);
        validateCodeFiler.afterPropertiesSet();
        http
                .addFilterBefore(validateCodeFiler,UsernamePasswordAuthenticationFilter.class);
    }
}
