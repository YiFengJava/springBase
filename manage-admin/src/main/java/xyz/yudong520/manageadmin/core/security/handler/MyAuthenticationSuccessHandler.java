package xyz.yudong520.manageadmin.core.security.handler;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import xyz.yudong520.manageadmin.core.security.authorized.ReturnType;
import xyz.yudong520.manageadmin.core.security.properties.SecurityCommon;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登陆返回的处理
 */
@Component
public class MyAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {


    //对象处理的工具
    @Autowired
    private ObjectMapper objectMapper;

    //日志打印
    private Logger logger = LoggerFactory.getLogger(MyAuthenticationSuccessHandler.class);

    @Autowired
    private SecurityCommon securityCommon;

    //请求转发的处理工具
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    private RequestCache requestCache = new HttpSessionRequestCache();

    private String targetUrl = "/index";

    /**
     * 登陆成功要执行的方法
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param authentication
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        logger.info("登陆成功！--");
        if(ReturnType.JSON.equals(securityCommon.getReturnType())){
            //设置返回类型
            httpServletResponse.setContentType("application/json;charset=UTF-8");
            //返回认证信息
            httpServletResponse.getWriter().write(objectMapper.writeValueAsString(authentication));
        }else{
            SavedRequest savedRequest = this.requestCache.getRequest(httpServletRequest, httpServletResponse);
            if (savedRequest == null) {
                super.onAuthenticationSuccess(httpServletRequest, httpServletResponse, authentication);
            } else {
                String targetUrlParameter = this.getTargetUrlParameter();
                if (!this.isAlwaysUseDefaultTargetUrl() && (targetUrlParameter == null || !StringUtils.hasText(httpServletRequest.getParameter(targetUrlParameter)))) {
                    this.clearAuthenticationAttributes(httpServletRequest);
//                String targetUrl = savedRequest.getRedirectUrl();
                    this.logger.debug("Redirecting to DefaultSavedRequest Url: " + targetUrl);
                    this.getRedirectStrategy().sendRedirect(httpServletRequest, httpServletResponse, targetUrl);
                } else {
                    this.requestCache.removeRequest(httpServletRequest, httpServletResponse);
                    super.onAuthenticationSuccess(httpServletRequest, httpServletResponse, authentication);
                }
        }
        }

//        super.onAuthenticationSuccess(httpServletRequest,httpServletResponse,authentication);
//        redirectStrategy.sendRedirect(httpServletRequest,httpServletResponse,"/");

        //执行该方法表示登陆成功 判断是否为Json
//            if(LoginType.JSON.equals(securityProperties.getBrowser().getLoginType())){
//                //设置返回类型
//                httpServletResponse.setContentType("application/json;charset=UTF-8");
//                //返回认证信息
//                httpServletResponse.getWriter().write(objectMapper.writeValueAsString(authentication));
//            }else{
//                //执行父类方法
//                super.onAuthenticationSuccess(httpServletRequest,httpServletResponse,authentication);
//            }

    }

    @Override
    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response) {
        return targetUrl;
    }
}
