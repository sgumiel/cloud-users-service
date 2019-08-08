package com.cloud.tutorial.usersservice.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.cloud.tutorial.usersservice.interceptors.RequestInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
 
    @Autowired
    private RequestInterceptor requestInterceptor;
    
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    	registry.addInterceptor(requestInterceptor).addPathPatterns("/users/**");
	}
 
    
}