package com.springAware;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

@Service
public class AwareService implements BeanNameAware ,ResourceLoaderAware { //实现BeanNameAware ResourceLoaderAware接口得到bean名称资源加载服务


    private String beanName;

    private ResourceLoader resourceLoader;


    @Override
    public void setBeanName(String s) {  //重写BeanNameAware的setBeanName接口
        this.beanName=s;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {    //重写ResourceLoaderAware的setResourceLoader接口
        this.resourceLoader=resourceLoader;
    }

    public void outputResult(){
        System.out.println("Bean的名称："+beanName);
        Resource resource=resourceLoader.getResource("classpath:aa.txt");
        try{
            System.out.println("ResourecLoader加载文件的内容："+ IOUtils.toString(resource.getInputStream()));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
