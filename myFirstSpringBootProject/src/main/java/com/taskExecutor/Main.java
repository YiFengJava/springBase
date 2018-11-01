package com.taskExecutor;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.task.TaskRejectedException;

public class Main {

    public static void main(String[] args) throws  InterruptedException{
        AnnotationConfigApplicationContext context =new AnnotationConfigApplicationContext(TaskExecutorConfig.class);
        AsyncTaskService bean = context.getBean(AsyncTaskService.class);
        for (int i=0 ; i<10; i++){
            try {
                bean.executeAsyncTask(i);
                bean.executeAsyncTaskPlus(i);
            } catch (TaskRejectedException e) {
                System.out.println("线程池满，等待1S。");
                Thread.sleep(1000);
            }
        }
        System.out.println("主程序==============");
        context.close();
    }
}
