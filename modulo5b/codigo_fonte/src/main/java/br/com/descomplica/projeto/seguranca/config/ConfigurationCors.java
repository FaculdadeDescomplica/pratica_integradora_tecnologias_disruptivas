package com.projeto.integrado.config;


import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class ConfigurationCors {

	/*
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
	    final CorsConfiguration config = new CorsConfiguration();

	    //config.setAllowedOrigins(Arrays.asList("http://localhost:3000", ""));
	    config.addAllowedOrigin("*");
	    config.setAllowedMethods(Arrays.asList("GET", "POST", "OPTIONS", "DELETE", "PUT", "PATCH"));
	    config.setAllowCredentials(true);
	    config.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", 
	    		"Content-Type", "Access-Control-Allow-Origin", "Access-Control-Allow-Headers",
	    		"X-Requested-With", "requestId", "Correlation-Id"
	    		)
	    );
	    
	    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", config);

	    return source;
	}
	*/
	
	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(false); 
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("GET");
		config.addAllowedMethod("PUT");
		config.addAllowedMethod("POST");
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}
	
}
