package com.infinity.app;

//Java Utility Imports
import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
//Spring Security Core Imports
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
//CORS Imports
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@SpringBootApplication
public class AtmIssueLoggerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AtmIssueLoggerApplication.class, args);
	}

/*	@Configuration
	@EnableWebSecurity
	public class SecurityConfig {
	    @Bean
	   SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	        http
	            .cors(cors -> cors.configurationSource(corsConfigurationSource()));
	            // other security configurations
	        return http.build();
	    }

	    @Bean
	    CorsConfigurationSource corsConfigurationSource() {
	        CorsConfiguration configuration = new CorsConfiguration();
	        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
	        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
	        configuration.setAllowedHeaders(Arrays.asList("*"));
	        configuration.setAllowCredentials(true);
	        
	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        source.registerCorsConfiguration("/**", configuration);
	        return source;
	    }
	}
*/

}