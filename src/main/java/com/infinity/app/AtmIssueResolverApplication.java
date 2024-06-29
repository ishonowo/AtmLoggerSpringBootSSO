package com.infinity.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication
public class AtmIssueResolverApplication {

	public static void main(String[] args) {
		SpringApplication.run(AtmIssueResolverApplication.class, args);
	}

/*	// Register the filter in your application configuration
	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilterRegistrationBean() {
		CorsConfiguration corsConfig = new CorsConfiguration();
		corsConfig.addAllowedOrigin("http://localhost:4200"); // Allowed origin
		corsConfig.addAllowedMethod("GET,POST,DELETE,OPTIONS"); // Allowed method
		corsConfig.addAllowedHeader("Content-Type"); // Allowed header

		// Create CorsFilter with the configuration
		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(
				new CorsFilter((CorsConfigurationSource) corsConfig));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
	}*/
	
	/*@Bean
	public CorsConfigurationSource corsConfigurationSource() {
	  UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	  CorsConfiguration config = new CorsConfiguration();
	  config.addAllowedOrigin("http://localhost:4200");
	  config.addAllowedMethod("*");
	  config.addAllowedHeader("*");
	  config.
	  source.registerCorsConfiguration("/**", config);
	  return source;
	}

	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter(CorsConfigurationSource corsConfigurationSource) {
	  FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(corsConfigurationSource));
	  return bean;
	}*/
	

}