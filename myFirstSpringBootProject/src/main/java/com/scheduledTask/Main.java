package com.scheduledTask;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TaskShedulerConfig.class);
        ScheduledTaskService bean = context.getBean(ScheduledTaskService.class);
    }
}
