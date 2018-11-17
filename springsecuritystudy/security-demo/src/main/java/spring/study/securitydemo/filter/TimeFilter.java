package spring.study.securitydemo.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

//@Component   也可以不用Componet注解直接注入也可以 用配置类注册
public class TimeFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
//        System.out.println("timefiler init======================================");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        System.out.println("timefiler start======================================");
        long startTime=System.currentTimeMillis();
        chain.doFilter(request,response);
        long endTime=System.currentTimeMillis();
//        System.out.println(" timefiler 耗時："+(endTime-startTime));
//        System.out.println("timefiler end======================================");
    }

    @Override
    public void destroy() {
//        System.out.println("timefiler destroy======================================");

    }
}
