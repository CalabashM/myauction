package com.shop.utils;






// WebMvcAutoConfigurationAdapter 底层是拦截器的机制


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {


    // 需要将本地磁盘的路径映射成网络路径

    // E:\pic\timg (2).jpg   -> http://localhost:8080/uploads/timg (2).jpg


    //file: 类似于一种写协议   http://



    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/uploads/**").addResourceLocations("file:E:\\pic\\");





    }
}
