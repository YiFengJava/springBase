package spring.study.securitycore.social.qq;

import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

public class MySocialConfig

        extends SpringSocialConfigurer
{

    private String signUpUrl;
    private String filterProcessesUrl;

    public MySocialConfig(String filterProcessesUrl,String signUpUrl){
        this.filterProcessesUrl = filterProcessesUrl;
        this.signUpUrl=signUpUrl;
    }

    @Override
    protected <T> T postProcess(T object) {
        SocialAuthenticationFilter filter = (SocialAuthenticationFilter) super.postProcess(object);
        filter.setFilterProcessesUrl(filterProcessesUrl);
        filter.setSignupUrl(signUpUrl);
        return (T) filter;
    }
}
