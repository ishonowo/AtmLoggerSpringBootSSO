package com.infinity.app.configuration;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Override
	protected void configure (HttpSecurity http) throws Exception{}
	/*@Override
	protected void configure (HttpSecurity http) throws Exception{
		
		http
        .cors(Customizer.withDefaults()) // by default uses a Bean by the name of corsConfigurationSource
        .authorizeRequests(auth -> auth
                .anyRequest().authenticated())
        	.oauth2Login()
			.defaultSuccessUrl("/atm/issue");
			
	}
	
	 @Bean
	    CorsConfigurationSource corsConfigurationSource() {
	        CorsConfiguration configuration = new CorsConfiguration();
	        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200","*"));
	        configuration.setAllowedMethods(Arrays.asList("GET","POST","DELETE"));
	        configuration.setAllowedHeaders(List.of("Authorization","*"));
	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        source.registerCorsConfiguration("/**", configuration);
	        return source;
	    }
	    */

/*	@Value("${clientId}")
	private String clientId;

	@Value("${clientSecret}")
	private String clientSecret;

	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.oauth2Login();
		http.authorizeRequests().anyRequest().authenticated();	
	}
	
	
	@Bean
	public ClientRegistrationRepository clientRegistrationRepository() {
		
		return new InMemoryClientRegistrationRepository(gitHubClient());
	}
	
	
	private ClientRegistration gitHubClient() {
		return CommonOAuth2Provider.GITHUB.getBuilder("github")
									.clientId("e3ef3e087bd80251e2e1")
									.clientSecret("dfb8c902f4ba1831e43d3fdb1c6427e03013c966")
									.build();
	}
	*/
}
