package com.shop;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.shop.mapper")
public class AppStarted {

    public static void main(String[] args) {
        SpringApplication.run(AppStarted.class,args);
    }
}
