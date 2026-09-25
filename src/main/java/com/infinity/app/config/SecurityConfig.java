package com.infinity.app.config;

import com.infinity.app.security.RoleNames;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.ldap.core.support.BaseLdapPathContextSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.ldap.LdapBindAuthenticationManagerFactory;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
// Lets you also use @PreAuthorize("hasRole('...')") directly on controller
// methods, as an alternative or supplement to the requestMatchers below.
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .csrf(AbstractHttpConfigurer::disable)
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                // Login itself: verified against ApacheDS: no JWT exists yet.
                .requestMatchers("/atm/auth/**").permitAll()

                // ---- Log an Issue ----
                .requestMatchers(HttpMethod.POST, "/atm/issue").hasRole(RoleNames.LOG_ISSUE)
                .requestMatchers(HttpMethod.POST, "/atm/email/**").hasRole(RoleNames.LOG_ISSUE)
                .requestMatchers(HttpMethod.GET, "/atm/database/fault/**").hasRole(RoleNames.LOG_ISSUE)

                // ---- Use Application Backend > Display / Insert / Update ----
                // These paths are illustrative, based on your menu labels.
                // Point them at your actual backend-data controller(s).
                .requestMatchers(HttpMethod.GET, "/atm/database/**").hasRole(RoleNames.DISPLAY)
                .requestMatchers(HttpMethod.POST, "/atm/database/**").hasRole(RoleNames.INSERT)
                .requestMatchers(HttpMethod.PUT, "/atm/database/**").hasRole(RoleNames.UPDATE)
                .requestMatchers(HttpMethod.DELETE, "/atm/delete/**").hasRole(RoleNames.UPDATE)

                // ---- Logged Calls ----
                .requestMatchers(HttpMethod.GET, "/atm/logged-calls/**").hasRole(RoleNames.LOGGED_CALLS)

                .anyRequest().authenticated()
            );

        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("https://localhost:4200"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setExposedHeaders(List.of("Authorization", "Content-Disposition"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    // Used only by AuthController, to verify credentials against ApacheDS at
    // login time (a direct LDAPS bind). This is separate from the
    // per-request JWT authentication handled by JwtAuthenticationFilter.
    //
    // Directory layout (confirmed in Apache Directory Studio):
    //   ou=system
    //     uid=admin              <- the app's own bind account (LdapConfig), not a login user
    //     ou=users
    //       cn=lola  (sn, mail, userPassword - no uid attribute)
    //       cn=ikenna
    // People entries have no "uid" attribute at all, only "cn", so the
    // search must match on cn, and the search base must be "ou=users"
    // (relative to contextSource's base of "ou=system") or the search never
    // descends into the subtree that actually holds the user entries.
    @Bean
    public AuthenticationManager authenticationManager(BaseLdapPathContextSource contextSource) {
        LdapBindAuthenticationManagerFactory factory =
                new LdapBindAuthenticationManagerFactory(contextSource);
        factory.setUserSearchFilter("(cn={0})");
        factory.setUserSearchBase("ou=users");
        return factory.createAuthenticationManager();
    }
}
