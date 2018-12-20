package xyz.yudong520.manageadmin.core.security.validate.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;
import xyz.yudong520.manageadmin.core.security.handler.MyAuthenticationFailureHandler;
import xyz.yudong520.manageadmin.core.security.properties.Commom;
import xyz.yudong520.manageadmin.core.security.properties.SecurityCommon;
import xyz.yudong520.manageadmin.core.security.validate.ValidateCodeProcessor;
import xyz.yudong520.manageadmin.core.security.validate.code.ValidateType;
import xyz.yudong520.manageadmin.core.security.validate.exception.ValidateException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Slf4j
public class ValidateCodeFiler extends OncePerRequestFilter  implements InitializingBean {


    private SecurityCommon securityCommon;


    //spring 提供的匹配器工具类
    private AntPathMatcher pathMatcher = new AntPathMatcher();


    private Map<String,ValidateCodeProcessor>  validateCodeProcessorMap;

    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;



    /**
     * 存放所有需要校验验证码的url
     */
    private Map<String, ValidateType> urlMap = new HashMap<>();

    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        //增加短信需要短信验证的地址
        urlMap.put(Commom.SMS_LOGIN_VALIDATE_URL,ValidateType.SMS); //增加登陆时短信校验
        //增加其他的需要短信校验的url
        urlAddToMap(securityCommon.getValidateCode().getSmsCode().getUrl(),ValidateType.SMS);

        //增加图片校验的地址
        urlMap.put(Commom.IMAGE_LOGIN_VALIDATE_URL,ValidateType.IMAGE);  //增加登陆
        urlAddToMap(securityCommon.getValidateCode().getImageCode().getUrl(),ValidateType.IMAGE);
    }

    private void urlAddToMap(String url, ValidateType validateType) {
        if(StringUtils.isNotBlank(url)){
            String[] urls = StringUtils.splitByWholeSeparatorPreserveAllTokens(url, ",");
            for(String u:urls){
                urlMap.put(u,validateType);
            }
        }
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        ValidateType validateType = getValidateType(httpServletRequest);
        if(validateType!=null){
            log.info("校验请求地址为："+httpServletRequest.getRequestURI()+",校验码的类型为："+validateType);
            String name=validateType.toString().toLowerCase()+ValidateCodeProcessor.class.getSimpleName();
            try {
                validateCodeProcessorMap.get(name).validateCode(new ServletWebRequest(httpServletRequest,httpServletResponse));
                log.info("校验码校验通过=====");
            }catch (ValidateException e){
                myAuthenticationFailureHandler.onAuthenticationFailure(httpServletRequest,httpServletResponse,e);
                return;
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }


    private ValidateType getValidateType(HttpServletRequest request){
        String requestURI = request.getRequestURI();
        ValidateType validateType =null;
        if(!StringUtils.equalsIgnoreCase(request.getMethod(),"get")){
            Set<String> strings = urlMap.keySet();
            for(String url:strings){
                if(pathMatcher.match(requestURI,url)){
                    log.info("改地址需要校验，该地址为："+requestURI);
                    validateType =urlMap.get(url);
                }
            }
        }
        return  validateType;

    }


    public SecurityCommon getSecurityCommon() {
        return securityCommon;
    }

    public void setSecurityCommon(SecurityCommon securityCommon) {
        this.securityCommon = securityCommon;
    }

    public Map<String, ValidateCodeProcessor> getValidateCodeProcessorMap() {
        return validateCodeProcessorMap;
    }

    public void setValidateCodeProcessorMap(Map<String, ValidateCodeProcessor> validateCodeProcessorMap) {
        this.validateCodeProcessorMap = validateCodeProcessorMap;
    }

    public MyAuthenticationFailureHandler getMyAuthenticationFailureHandler() {
        return myAuthenticationFailureHandler;
    }

    public void setMyAuthenticationFailureHandler(MyAuthenticationFailureHandler myAuthenticationFailureHandler) {
        this.myAuthenticationFailureHandler = myAuthenticationFailureHandler;
    }
}
