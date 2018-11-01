package com.javaBeanBaseDome;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration//当前类是一个配置类
public class JavaBeanConfig {

    @Bean   //当前方法返回一个bean 名称为默认为当前反方名
    public FunctionService functionService(){
        return  new FunctionService();
    }

    @Bean
    public UserFunctionService userFunctionService(){
        UserFunctionService userFunctionService = new UserFunctionService();
        userFunctionService.setFunctionService(functionService());  //注入functionService时可以直接调用functionService()
        return  userFunctionService;
    }

//    @Bean
//    public  UserFunctionService userFunctionService(FunctionService functionService){  //将FunctionService 作为参数传入
//        UserFunctionService userFunctionService = new UserFunctionService();
//        userFunctionService.setFunctionService(functionService);
//        return  userFunctionService;
//    }
}
