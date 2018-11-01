package com.InitDestoryBeanDome;

public class BeanWayService {

    public void  init(){
        System.out.println("@Bean-init_method");
    }

    public BeanWayService(){
        super();
        System.out.println("初始化构造函数-BeanWayService");
    }

    public void destroy(){
        System.out.println("@Bean-destory-method");
    }
}
