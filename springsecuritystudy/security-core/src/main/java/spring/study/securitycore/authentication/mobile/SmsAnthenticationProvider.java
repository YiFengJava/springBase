package spring.study.securitycore.authentication.mobile;

import lombok.Data;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
@Data
public class SmsAnthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SmsCodeAuthenticationToken smsCodeAuthenticationToken=(SmsCodeAuthenticationToken) authentication;
        UserDetails userDetails = userDetailsService.loadUserByUsername(smsCodeAuthenticationToken.getPrincipal().toString());
        if(userDetails==null){
            throw new InternalAuthenticationServiceException("无法获取用户信息");
        }
        SmsCodeAuthenticationToken resultSmsCodeAuthenticationToken = new SmsCodeAuthenticationToken(userDetails, userDetails.getAuthorities());
        resultSmsCodeAuthenticationToken.setDetails(smsCodeAuthenticationToken.getDetails());
        return resultSmsCodeAuthenticationToken;
    }

    @Override
    public boolean supports(Class<?> authencation) {
        boolean boo = SmsCodeAuthenticationToken.class.isAssignableFrom(authencation);
        return boo;
    }
}
