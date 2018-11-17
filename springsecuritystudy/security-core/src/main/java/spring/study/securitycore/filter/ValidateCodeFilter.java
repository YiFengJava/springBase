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

import spring.study.securitycore.validate.sms.ValidateCode;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * 图片校验过滤器
 */
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {//spring提供的工具类保证,过滤器只能调用一次


    //校验失败时出来
    private AuthenticationFailureHandler authenticationFailureHandler;

    //Spring提供的操作session的工具类
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    //需要校验的url集合
    private Set<String> urls = new HashSet<>();

    //自定义的配置类
    private SecurityProperties securityProperties;

    //spring 提供的匹配器工具类
    private AntPathMatcher pathMatcher = new AntPathMatcher();

    /**
     * 由 InitializingBean提供，可以初始化bean
     *
     * @throws ServletException
     */
    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        String[] strings = StringUtils.splitByWholeSeparator(securityProperties.getValidate().getImage().getUrl(), ",");
        if (strings != null) {
            for (String url : strings) {
                if (StringUtils.isNotBlank(url)) {
                    urls.add(url);
                }

            }
        }
        urls.add("/anthentication/form");
    }


    /**
     * 过滤器执行的方法
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        boolean flag = false;
        for (String url : urls) {
            //判断请求的url 是否是要求校验的url
            if (pathMatcher.match(url, httpServletRequest.getRequestURI())) {
                flag = true;
            }
        }
//        if(StringUtils.equals("/anthentication/form",httpServletRequest.getRequestURI())
//                && (StringUtils.equals(httpServletRequest.getMethod(),"post") || StringUtils.equals(httpServletRequest.getMethod(),"POST"))){
        if (flag) {
            try {
                //校验验证码是否正确
                validate(new ServletWebRequest(httpServletRequest));
            } catch (ValidateCodeException ex){
                //交给登陆失败处理
                authenticationFailureHandler.onAuthenticationFailure(httpServletRequest, httpServletResponse, ex);
                //直接返回
                return;
            }
        }
        //执行下一个过滤器
        filterChain.doFilter(httpServletRequest, httpServletResponse);

    }

    /**
     * 校验验证码是否正确
     * @param servletWebRequest
     * @throws ValidateCodeException
     * @throws ServletRequestBindingException
     */
    private void validate(ServletWebRequest servletWebRequest) throws ValidateCodeException, ServletRequestBindingException {
        //得到存放在session中的校验码
        ValidateCode session_image = (ValidateCode) sessionStrategy.getAttribute(servletWebRequest, "SESSION_KEY_FOR_CODE_IMAGE");//SESSION_KEY_FOR_CODE_SMS
        //得到前台传过来的验证码
        String imageCodeRequest = ServletRequestUtils.getStringParameter(servletWebRequest.getRequest(), "imageCode");
        //比较验证码的正确性，并返回相应的信息
        if (StringUtils.isBlank(imageCodeRequest)) {
            throw new ValidateCodeException("验证码不能为空！");
        }
        if (session_image == null) {
            throw new ValidateCodeException("验证码不存在！");
        }
        if (StringUtils.isBlank(imageCodeRequest)) {
            throw new ValidateCodeException("验证码不能为空！");
        }
        if (session_image.isExpried()) {
            sessionStrategy.removeAttribute(servletWebRequest, "SESSION_KEY_FOR_CODE_IMAGE");
            throw new ValidateCodeException("验证码已过期！");
        }
        if (!StringUtils.equals(session_image.getCode(), imageCodeRequest)) {
            throw new ValidateCodeException("验证码不匹配！");
        }
        sessionStrategy.removeAttribute(servletWebRequest, "SESSION_KEY_FOR_CODE_IMAGE");
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
