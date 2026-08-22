package com.infinity.app;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AtmIssueLoggerApplication {

	public static void main(String[] args) {
		System.setProperty("javax.net.ssl.trustStore", "classpath:ldap-truststore2.jks");
        System.setProperty("javax.net.ssl.trustStorePassword", "changeit");
        
     // Force older, ApacheDS-compatible TLS protocol — JDK 25 defaults to
        // offering TLSv1.3, which ApacheDS 2.0.0's aging SSL stack can't negotiate.
        System.setProperty("jdk.tls.client.protocols", "TLSv1.2");
        SpringApplication.run(AtmIssueLoggerApplication.class, args);
	}

}