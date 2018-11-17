package spring.study.securitycore.authentication.handler;



import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import spring.study.securitycore.properties.LoginType;
import spring.study.securitycore.properties.SecurityProperties;

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
    private Logger logger= LoggerFactory.getLogger(MyAuthenticationSuccessHandler.class);


    //自定义安全配置
    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 登陆成功要执行的方法
     * @param httpServletRequest
     * @param httpServletResponse
     * @param authentication
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
            logger.info("登陆成功！--");
            //执行该方法表示登陆成功 判断是否为Json
            if(LoginType.JSON.equals(securityProperties.getBrowser().getLoginType())){
                //设置返回类型
                httpServletResponse.setContentType("application/json;charset=UTF-8");
                //返回认证信息
                httpServletResponse.getWriter().write(objectMapper.writeValueAsString(authentication));
            }else{
                //执行父类方法
                super.onAuthenticationSuccess(httpServletRequest,httpServletResponse,authentication);
            }

    }
}
