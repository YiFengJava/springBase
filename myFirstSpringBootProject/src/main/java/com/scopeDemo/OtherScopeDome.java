package com.scopeDemo;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
//@Scope("singleton") //  一个spring容器中只有一个这样的bean,也是spring的默认配置，全容器共享一个实例
@Scope("prototype")  //每次调用都会新建一个bean实例
//@Scope("request")   //在web项目中，给每个http request新建一个bean实例
//@Scope("session")   //在web项目中，给每个http session新建一个bean实例
//@Scope("globalSession")   //在portal应用中。给每个global http session 新建一个bean实例
public class OtherScopeDome {
}
