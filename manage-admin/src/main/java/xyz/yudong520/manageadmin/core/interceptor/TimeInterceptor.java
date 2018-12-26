package xyz.yudong520.manageadmin.core.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class TimeInterceptor implements HandlerInterceptor{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       log.info("方法开始执行========================================================");
        //调用之前
//        System.out.prin//tln("preHandle");
//        System.out.println(((HandlerMethod)handler).getBean().getClass().getName());
//        System.out.printl/n(((HandlerMethod)handler).getMethod().getName());
        request.setAttribute("startTime",System.currentTimeMillis());
        request.setAttribute("isException",true);
        return true;
    }
//    DispatcherServlet
//    org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("方法执行正常完结========================================================");
        Long startTime = (Long) request.getAttribute("startTime");
        request.setAttribute("isException",false);
       log.info("time interceptor耗时："+(System.currentTimeMillis()-startTime));
            //处理了方法之后  异常就不会调用\
//        System.out.println("postHandle");

//        System.out.println("time interceptor耗时："+(System.currentTimeMillis()-startTime));
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //之后会条用 异常也会调用
//        System.out.println("afterCompletion");
        boolean isException = (boolean) request.getAttribute("isException");
        if(isException){
            log.info("方法执行异常完结========================================================");
            Long startTime = (Long) request.getAttribute("startTime");
            request.removeAttribute("startTime");
            log.info("time interceptor耗时："+(System.currentTimeMillis()-startTime));
        }
        request.removeAttribute("isException");
//        System.out.println("time interceptor耗时："+(System.currentTimeMillis()-startTime));
//        System.out.println("es is"+ ex);
    }
}
