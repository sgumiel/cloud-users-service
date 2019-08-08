package com.cloud.tutorial.usersservice.filters;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.MDC;
import org.springframework.web.filter.OncePerRequestFilter;

public class AddMDCFilter extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		final String id = UUID.randomUUID().toString();
    	MDC.put("idd", id);
    	
    	filterChain.doFilter(request, response);
    	
    	MDC.remove("idd");
		
		
	
	}

}
