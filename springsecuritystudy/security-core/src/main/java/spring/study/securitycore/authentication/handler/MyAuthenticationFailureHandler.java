package spring.study.securitycore.authentication.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import spring.study.securitycore.authentication.support.SimpleResponse;
import spring.study.securitycore.properties.LoginType;
import spring.study.securitycore.properties.SecurityProperties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登陆失败的处理
 */
@Component
public class MyAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    //spring中的对对象的处理
    @Autowired
    private ObjectMapper objectMapper;

    //安全配置对象
    @Autowired
    private SecurityProperties securityProperties;

    //日志器
    private Logger logger= LoggerFactory.getLogger(AuthenticationFailureHandler.class);

    /**
     * 登陆失败后会执行该方法
     * @param httpServletRequest
     * @param httpServletResponse
     * @param e
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        logger.info("登陆失败！--");
        //根据配置的登陆类型是否是返回json 还是页面跳转
        if(LoginType.JSON.equals(securityProperties.getBrowser().getLoginType())){
//            返回状态码为服务器异常 500
            httpServletResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
//            设置comtenttype
            httpServletResponse.setContentType("application/json;charset=UTF-8");
//            返回给页面
            httpServletResponse.getWriter().write(objectMapper.writeValueAsString(new SimpleResponse(e.getMessage())));
        }else {
            //如果是配的是页面跳转 则掉父类的方法
            super.onAuthenticationFailure(httpServletRequest,httpServletResponse,e);
        }

    }
}
