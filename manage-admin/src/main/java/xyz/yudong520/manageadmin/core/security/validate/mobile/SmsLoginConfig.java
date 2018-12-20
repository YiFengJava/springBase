package xyz.yudong520.manageadmin.core.security.validate.mobile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import xyz.yudong520.manageadmin.core.security.handler.MyAuthenticationFailureHandler;
import xyz.yudong520.manageadmin.core.security.handler.MyAuthenticationSuccessHandler;
import xyz.yudong520.manageadmin.core.security.service.SecurityService;
import xyz.yudong520.manageadmin.core.security.validate.mobile.filter.SmsCodeAuthenticationFilter;
import xyz.yudong520.manageadmin.core.security.validate.mobile.provider.SmsMobileAuthenticationProvider;

@Component
public class SmsLoginConfig  extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Autowired
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;
    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Autowired
    private SecurityService securityService;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        SmsCodeAuthenticationFilter smsCodeAuthenticationFilter = new SmsCodeAuthenticationFilter();
        smsCodeAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        smsCodeAuthenticationFilter.setAuthenticationFailureHandler(myAuthenticationFailureHandler);
        smsCodeAuthenticationFilter.setAuthenticationSuccessHandler(myAuthenticationSuccessHandler);

        SmsMobileAuthenticationProvider smsMobileAuthenticationProvider=new SmsMobileAuthenticationProvider();
        smsMobileAuthenticationProvider.setSecurityService(securityService);

        http
                .authenticationProvider(smsMobileAuthenticationProvider)
                .addFilterBefore(smsCodeAuthenticationFilter,UsernamePasswordAuthenticationFilter.class);
    }
}
