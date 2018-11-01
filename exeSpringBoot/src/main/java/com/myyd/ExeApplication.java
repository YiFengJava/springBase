package com.myyd;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan(value = "com.myyd.dao")
@SpringBootApplication
public class ExeApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExeApplication.class, args);
    }
}
