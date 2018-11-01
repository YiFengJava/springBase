package com.scheduledTask;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ScheduledTaskService {
    private static  final SimpleDateFormat  dateFormat=new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 5000) //1  该注解是声明计划任务， 使用fixedRate属性是固定间隔时间执行
    public void reportCurrentTime(){
        System.out.println("每隔五秒执行一次" + dateFormat.format(new Date()));
    }
                      // 分 时
    @Scheduled(cron = "0 28 16 ? * *") //2 使用cron属性可按照指定执行，本例指的是每天11点28分执行；cron是unix和类unix(linux)系统下的时间定时任务
    public void fixTimeExecution(){
        System.out.println("在指定时间 "+ dateFormat.format(new Date())+"执行");
    }
}
