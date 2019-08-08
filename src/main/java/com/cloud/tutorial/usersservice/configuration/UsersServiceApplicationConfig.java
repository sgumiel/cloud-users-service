package com.cloud.tutorial.usersservice.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import com.cloud.tutorial.usersservice.filters.AddMDCFilter;

@Configuration
public class UsersServiceApplicationConfig {

	@Bean 
	public AddMDCFilter createMDCFilter() {
		return new AddMDCFilter();
	}
	
	@Bean
	public CommonsRequestLoggingFilter createCommonsRequestFilter() {

		final CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
		filter.setIncludeClientInfo(true);
		filter.setIncludeQueryString(true);
		filter.setIncludeHeaders(true);
		filter.setIncludePayload(true);
		return filter;

	}

}
