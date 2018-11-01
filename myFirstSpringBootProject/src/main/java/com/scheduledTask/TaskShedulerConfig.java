package com.scheduledTask;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@ComponentScan("com.scheduledTask")
@EnableScheduling  //开启计划任务的支持
public class TaskShedulerConfig {
}
