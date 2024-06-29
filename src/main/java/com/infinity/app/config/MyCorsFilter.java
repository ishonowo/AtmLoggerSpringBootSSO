package com.infinity.app.config;
/*
import java.io.IOException;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.CorsFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
//import jakarta.servlet.ServletRequest;
//import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.Filter;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class MyCorsFilter extends OncePerRequestFilter {

	private final String allowedOrigin = "http://localhost:4200";

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain)
			throws ServletException, IOException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		String origin = request.getHeader("Origin");
		if (origin != null && 
				origin.equals(allowedOrigin)) {
			response.setHeader("Access-Control-Allow-Origin", origin);
			response.setHeader("Access-Control-Allow-Credentials", "true"); // Allow credentials for cookies (if
																			// applicable)
			response.setHeader("Access-Control-Allow-Methods", "GET, POST, DELETE"); // Adjust allowed methods
			response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization"); // Adjust allowed headers
			// **Not recommended for production - consider refactoring backend logic**
			if (request.getRequestURI().equals("/atm/issue") && request.getMethod().equals("OPTIONS")) { // Only for
																											// preflight
																											// requests
																											// for
																											// /atm/issue
				response.setHeader("Access-Control-Allow-Methods",
						response.getHeader("Access-Control-Allow-Methods") + ", 302"); // Allow 302 Found redirect (not
																						// recommended)
			}
		}
		filterChain.doFilter(req, res);
	}

	
		// TODO Auto-generated method stub
		
}
*/