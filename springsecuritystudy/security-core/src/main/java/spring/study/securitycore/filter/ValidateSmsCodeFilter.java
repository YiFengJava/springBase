package spring.study.securitycore.filter;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;
import spring.study.securitycore.exception.ValidateCodeException;
import spring.study.securitycore.properties.SecurityProperties;
import spring.study.securitycore.validate.ValidateController;
import spring.study.securitycore.validate.image.ImageCode;
import spring.study.securitycore.validate.sms.ValidateCode;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;


public class ValidateSmsCodeFilter extends OncePerRequestFilter implements InitializingBean{//spring提供的工具类保证,过滤器只能调用一次

    private AuthenticationFailureHandler authenticationFailureHandler;

    private SessionStrategy sessionStrategy=new HttpSessionSessionStrategy();

    private Set<String> urls =new HashSet<>();

    private SecurityProperties securityProperties;

    private AntPathMatcher pathMatcher=new AntPathMatcher();
    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        String[] strings = StringUtils.splitByWholeSeparator(securityProperties.getValidate().getSmsCode().getUrl(), ",");
       if(strings!=null){
           for(String url:strings){
               if(StringUtils.isNotBlank(url)){
                   urls.add(url);
               }

           }
       }
        urls.add("/anthentication/mobile");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        boolean flag=false;
        for (String url:urls) {
            if(pathMatcher.match(url,httpServletRequest.getRequestURI())){
                flag=true;
            }
        }
//        if(StringUtils.equals("/anthentication/form",httpServletRequest.getRequestURI())
//                && (StringUtils.equals(httpServletRequest.getMethod(),"post") || StringUtils.equals(httpServletRequest.getMethod(),"POST"))){
          if(flag){
            try {
                validate(new ServletWebRequest(httpServletRequest));
            }catch (ValidateCodeException ex){
                authenticationFailureHandler.onAuthenticationFailure(httpServletRequest,httpServletResponse,ex);
                return;
            }
        }
            filterChain.doFilter(httpServletRequest,httpServletResponse);

    }

    private void validate(ServletWebRequest servletWebRequest) throws ValidateCodeException, ServletRequestBindingException {

        ValidateCode validateCode = (ValidateCode) sessionStrategy.getAttribute(servletWebRequest, "SESSION_KEY_FOR_CODE_SMS");
        String smsCodeRequest= ServletRequestUtils.getStringParameter(servletWebRequest.getRequest(), "smsCode");
        if(StringUtils.isBlank(smsCodeRequest)){
            throw new ValidateCodeException("验证码不能为空！");
        }
        if(validateCode==null){
            throw new ValidateCodeException("验证码不存在！");
        }
        if(StringUtils.isBlank(smsCodeRequest)){
            throw new ValidateCodeException("验证码不能为空！");
        }
        if(validateCode.isExpried()){
            sessionStrategy.removeAttribute(servletWebRequest,"SESSION_KEY_FOR_CODE_SMS");
            throw new ValidateCodeException("验证码已过期！");
        }
        if(!StringUtils.equals(validateCode.getCode(),smsCodeRequest)){
            throw new ValidateCodeException("验证码不匹配！");
        }
        sessionStrategy.removeAttribute(servletWebRequest, "SESSION_KEY_FOR_CODE_SMS");
    }


    public AuthenticationFailureHandler getAuthenticationFailureHandler() {
        return authenticationFailureHandler;
    }

    public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationFailureHandler = authenticationFailureHandler;
    }


    public SecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

}
