package springmvc.mvcinterceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.List;

@Configuration
@EnableScheduling
@ComponentScan("springmvc.mvcinterceptor")
@EnableWebMvc  //1 @EnableWebMvc开启springMVC支持 ，若无此句，重写WebMvcConfigurerAdapter方法无效
public class MyMvcConfig  extends WebMvcConfigurerAdapter{ //2  继承WebMvcConfigurerAdapter类，重写其方法可对springMVC进行配置

    //视图渲染
    @Bean
    public InternalResourceViewResolver internalResourceViewResolver(){
        InternalResourceViewResolver viewResolver
                =new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/classes/views/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setViewClass(JstlView.class);
        return  viewResolver;
    }

//    静态资源映射
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //addResourceLocations指的是文件放置的目录，addResourceHandler指的是指对外暴露的访问路径
        registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/assets/");//3
    }

    @Bean //配置拦截器的bean
    public DemoInterceptor domeInterceptor(){
        return  new DemoInterceptor();
    }


    //重写addInterceptors方法。注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(domeInterceptor());
    }

    @Bean
    public MyMessageConverter myMessageConverter(){
        return  new MyMessageConverter();
    }

    /**
     * 配置自定义的HttpMessageConverter 的Bean ，在Spring MVC 里注册HttpMessageConverter有两个方法：
     * 1、configureMessageConverters ：重载会覆盖掉Spring MVC 默认注册的多个HttpMessageConverter
     * 2、extendMessageConverters ：仅添加一个自定义的HttpMessageConverter ，不覆盖默认注册的HttpMessageConverter
     * 在这里重写extendMessageConverters
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(myMessageConverter());

    }

}
