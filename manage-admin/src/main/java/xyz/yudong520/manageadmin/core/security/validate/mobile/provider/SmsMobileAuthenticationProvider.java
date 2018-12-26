package xyz.yudong520.manageadmin.core.security.validate.mobile.provider;

import lombok.Data;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import xyz.yudong520.manageadmin.core.security.service.SecurityService;
import xyz.yudong520.manageadmin.core.security.validate.mobile.token.SmsMobileAuthenticationToken;
import xyz.yudong520.manageadmin.system.entity.User;

@Data
public class SmsMobileAuthenticationProvider implements AuthenticationProvider {

    private SecurityService securityService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SmsMobileAuthenticationToken token=(SmsMobileAuthenticationToken) authentication;
        String mobile =(String) token.getPrincipal();
        User user = securityService.loadUserByUsername(mobile);
        if(user==null){
            throw new InternalAuthenticationServiceException("无法获取用户信息");
        }
        SmsMobileAuthenticationToken token1 = new SmsMobileAuthenticationToken(user, user.getAuthorities());
        token1.setDetails(token.getDetails());
        return token1;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        boolean boo = SmsMobileAuthenticationToken.class.isAssignableFrom(aClass);
        return boo;
    }
}
