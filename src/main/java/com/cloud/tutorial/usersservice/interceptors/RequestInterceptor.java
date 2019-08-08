package com.cloud.tutorial.usersservice.interceptors;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class RequestInterceptor implements HandlerInterceptor {
	
	private static final Logger logger = LoggerFactory.getLogger(RequestInterceptor.class);
	
	@Autowired
	private Environment environment;
 
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
//    	final String id = UUID.randomUUID().toString();
//    	MDC.put("idd", id);
//    	logger.debug("Users service in: {}", environment.getProperty("local.server.port"));
        return true;
    }
 
    @Override
    public void afterCompletion(HttpServletRequest request,  HttpServletResponse response, Object handler, Exception ex) {
//    	logger.debug("Users service response: {}", response);
//    	MDC.remove("idd");
    }

}