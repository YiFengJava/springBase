package springmvc.mvcinterceptor;


import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class WebInitializer  implements WebApplicationInitializer { //1 WebApplicationInitializer是spring提供用来配置servlet3.0配置的接口，
//    从而实现代替web.xml的位置，实现此接口将会自动被SpringServletContainerInitiaLizer(用来启动Servliet的容器)获取到
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext acwc=new AnnotationConfigWebApplicationContext();
        acwc.register(MyMvcConfig.class);
        acwc.setServletContext(servletContext); //2 新建WebApplicationContext，注册配置类，并将其和挡前servletContext关联
        //3 注册springmvc 的DispatcherServlet
        ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(acwc));
        servlet.addMapping("/");
        servlet.setLoadOnStartup(1);
        // 开启异步方法支持
        servlet.setAsyncSupported(true);
    }
}
