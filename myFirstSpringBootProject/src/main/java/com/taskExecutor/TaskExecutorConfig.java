package com.taskExecutor;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;

@Configuration
@ComponentScan("com.taskExecutor")
@EnableAsync  //  1 开启异步任务支持
public class TaskExecutorConfig  implements AsyncConfigurer{ //2 配置类实现AsyncConfigurer接口并重写getAsyncExecutor方法，并返回ThreadPoolTaskExecutor 这样我们就获得了一个基于线程池TaskExecutor

    // ThredPoolTaskExcutor的处理流程
    // 当池子大小小于corePoolSize，就新建线程，并处理请求
  // 当池子大小等于corePoolSize，把请求放入workQueue中，池子里的空闲线程就去workQueue中取任务并处理
   // 当workQueue放不下任务时，就新建线程入池，并处理请求，如果池子大小撑到了maximumPoolSize，就用RejectedExecutionHandler来做拒绝处理
  // 当池子的线程数大于corePoolSize时，多余的线程会等待keepAliveTime长时间，如果无请求可处理就自行销毁
    @Override
    public Executor getAsyncExecutor() {  //
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(5);  // 最小线程数
        taskExecutor.setMaxPoolSize(10);  // 最大线程数
        taskExecutor.setQueueCapacity(25);  // 等待队列
        taskExecutor.initialize();
        return taskExecutor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new SimpleAsyncUncaughtExceptionHandler();
    }

    public static void main(String[] args) {
        String s="我ABC";
        int a=s.length();
        String d = getthe(s, a);
        System.out.println(d);

    }

    public  static  String getthe(String s,int a){
        if(s==null || s.trim().equals("")){
            return  s;
        }
        byte[] bytes = s.getBytes();
        System.out.println(bytes.length);
        byte[] newb=new byte[a];
        for (int i=0;i<a;i++){
            byte b=bytes[i];
            newb[i]=b;
        }
        String s1 = new String(newb);
        if(s.indexOf(s1)==-1){
            s1=s1.substring(0,s1.length()-1);
        }
        return  s1;
    }
}
