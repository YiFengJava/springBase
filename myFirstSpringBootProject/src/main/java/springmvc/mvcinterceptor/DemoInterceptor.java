package springmvc.mvcinterceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DemoInterceptor extends HandlerInterceptorAdapter{  //继承HandlerInterceptorAdapter类来实现自定义拦截器
    //重写preHandle方法，在请求发生之前执行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       Long startTime=System.currentTimeMillis();
       request.setAttribute("startTime",startTime);
       return  true;
    }

    //重写postHandle方法，在请求完成后执行
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        Long startTime = (Long) request.getAttribute("startTime");
        request.removeAttribute("startTime");
        Long endTime=System.currentTimeMillis();
        System.out.println("本次请求处理时间为："+new Long(endTime-startTime)+"ms");
        request.setAttribute("handlingTime",endTime-startTime);
    }
}
