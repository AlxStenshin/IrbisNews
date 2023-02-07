package ru.alxstn.irbisnews.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import ru.alxstn.irbisnews.properties.AuthConfigurationProperties;
import ru.alxstn.irbisnews.security.APIKeyAuthFilter;

@Configuration
@EnableWebSecurity
public class APISecurityConfig {

    private final AuthConfigurationProperties authProperties;

    public APISecurityConfig(AuthConfigurationProperties authProperties) {
        this.authProperties = authProperties;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        APIKeyAuthFilter filter = new APIKeyAuthFilter(authProperties.getHeader());
        filter.setAuthenticationManager(authentication -> {
            String principal = (String) authentication.getPrincipal();
            if (!authProperties.getToken().equals(principal)) {
                throw new BadCredentialsException("The API key was not found or not the expected value.");
            }
            authentication.setAuthenticated(true);
            return authentication;
        });

        http.securityMatcher("/html/**").anonymous();
        http.securityMatcher("/swagger-ui/**", "/v3/api-docs/**").anonymous();

        http.securityMatcher("/api/**").
                csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(filter).authorizeHttpRequests().anyRequest().authenticated();

        return http.build();
    }
}