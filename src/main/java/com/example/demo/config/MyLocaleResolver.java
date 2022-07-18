package com.example.demo.config;

import org.springframework.web.servlet.LocaleResolver;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class MyLocaleResolver implements LocaleResolver {
    //解析请求，重写代码
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        //获取请求中的语言参数链接
        String language=request.getParameter("l");

        Locale locale=Locale.getDefault();//如果没有进行赋值，那么就是用默认值

        //判断传入是否为空
        if(!StringUtils.isEmpty(language)){
            //切割地区
            String[] split=language.split("_");
            //国家，地区
            locale=new Locale(split[0],split[1]);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}
