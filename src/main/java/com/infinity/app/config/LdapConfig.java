package com.infinity.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.support.LdapContextSource;

@Configuration
public class LdapConfig {

    @Bean
    public LdapContextSource contextSource() {
        LdapContextSource contextSource = new LdapContextSource();
        contextSource.setUrl("ldaps://localhost:10636");
        contextSource.setBase("ou=users,ou=system");
        contextSource.setUserDn("uid=admin,ou=system");
        contextSource.setPassword("secret");

        // Ensures TLS is used for the connection
        contextSource.setPooled(false);
        return contextSource;
    }
}