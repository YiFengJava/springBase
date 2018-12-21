package xyz.yudong520.manageadmin.core.security.authorized.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import xyz.yudong520.manageadmin.core.security.authorized.Authorized;
import xyz.yudong520.manageadmin.core.security.authorized.ReturnType;
import xyz.yudong520.manageadmin.system.controller.exception.NotAuthorityHtmlException;
import xyz.yudong520.manageadmin.system.controller.exception.NotAuthorityJsonException;
import xyz.yudong520.manageadmin.system.entity.Permissions;
import xyz.yudong520.manageadmin.system.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Set;

@Slf4j
@Component
@Aspect
public class AuthorizedAspect {


    @Autowired
    private HttpServletRequest request;

    //spring 提供的匹配器工具类
    private AntPathMatcher pathMatcher = new AntPathMatcher();


    @Pointcut(value = "@annotation(xyz.yudong520.manageadmin.core.security.authorized.Authorized)")
    public void authorizedCut(){
    }

    @Around(value ="authorizedCut()")
    public Object recordSysLog(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();  //获取拦截的类
        Method method = signature.getMethod();// 获取拦截的方法
        String name = method.getName();
        Authorized annotation = method.getAnnotation(Authorized.class);
        HttpMethod method1 = annotation.method();
        ReturnType type = annotation.type();
        String value = annotation.value();
        String method2 = request.getMethod();
        String requestURI = request.getRequestURI();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //在授权信息中获取用户个人基本信息
        User user = (User) authentication.getPrincipal();
        //获取管理员的所有的权限列表
        Set<Permissions> authorities = user.getAuthorities();

        for (Permissions permissions:authorities){
            String value1 = permissions.getValue();
            if(pathMatcher.match(requestURI,value1) && method1.toString().toUpperCase().equals(method2.toString().toUpperCase())){
                Object proceed = point.proceed();
                return  proceed;
            }
        }
        if(ReturnType.JSON.toString().equals(type.toString())){
            throw new NotAuthorityJsonException("权限不足，请联系管理员！");
        }
        if(ReturnType.HTML.toString().equals(type.toString())){
            throw new NotAuthorityHtmlException("权限不足，请联系管理员！");
        }

        return  null;
    }
}
