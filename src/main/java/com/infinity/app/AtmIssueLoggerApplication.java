package com.infinity.app;



import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class AtmIssueLoggerApplication {

	public static void main(String[] args) throws IOException {
	      try (InputStream in = AtmIssueLoggerApplication.class
	                .getResourceAsStream("/ldap-truststore.jks")) {
	            if (in == null) {
	                throw new IllegalStateException("ldap-truststore.jks not found on classpath");
	            }
	            File tempTrustStore = File.createTempFile("ldap-truststore", ".jks");
	            tempTrustStore.deleteOnExit();
	            Files.copy(in, tempTrustStore.toPath(), StandardCopyOption.REPLACE_EXISTING);

	            System.setProperty("javax.net.ssl.trustStore", tempTrustStore.getAbsolutePath());
	            System.setProperty("javax.net.ssl.trustStorePassword", "changeit");
	        }

	        System.setProperty("jdk.tls.client.protocols", "TLSv1.2");
	        System.out.println("trustStore = " + System.getProperty("javax.net.ssl.trustStore"));

	        SpringApplication.run(AtmIssueLoggerApplication.class, args);
	}

}