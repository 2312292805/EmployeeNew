package com.example.demo.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//如果是想要自动填充一些功能容器，那么只需要撰写这个组件，然后将其交给SpringBoot,SpringBoot就会自动进行装配
//全面接管SpringMVC
@Configuration
public class MyMvcConfig implements WebMvcConfigurer{
    //扩展MVC功能
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //访问路径
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index.html").setViewName("index");
        registry.addViewController("/main.html").setViewName("dashboard");
    }
    //自定义国家化主键生效
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }
    //配置拦截器信息
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**").
                excludePathPatterns("/index.html","/","/user/login","/css/*","/js/**","/img/**");
//excludePathPatterns时进行定义拦截器放行的文件路径
    }
}
