package spring.study.securityBrowser.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import spring.study.securityBrowser.support.SimpleResponse;
import spring.study.securitycore.properties.SecurityConstants;
import spring.study.securitycore.properties.SecurityProperties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class SecurityBrowserController {

    private Logger logger =LoggerFactory.getLogger(SecurityBrowserController.class);

    //请求的缓存  security中的请求缓存工具类
    private RequestCache requestCache=new HttpSessionRequestCache();

    //请求转发的处理工具
    private RedirectStrategy redirectStrategy =new DefaultRedirectStrategy();

    //自己的配置类
    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 当需要认证时跳转到这里
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED) //返回状态码 未授权401
    public SimpleResponse requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //从缓存中得到请求的缓存
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if(savedRequest!=null ){
            //得到请求的地址
            String targetUrl=savedRequest.getRedirectUrl();
            logger.info("引发跳转的请求是："+targetUrl);
            if(StringUtils.endsWithIgnoreCase(targetUrl,".html")){
                //直接跳转到配置的登陆页面
                redirectStrategy.sendRedirect(request,response,securityProperties.getBrowser().getLoginPage());
            }
        }
        return new SimpleResponse("访问的五福需要身份认证，请引导用户到登陆页面！");
    }
}
