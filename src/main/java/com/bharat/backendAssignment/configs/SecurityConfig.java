package com.bharat.backendAssignment.configs;

import com.bharat.backendAssignment.filters.FirebaseTokenAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final FirebaseTokenAuthFilter firebaseTokenAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        assert firebaseTokenAuthFilter != null;

        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(registry -> {
                    registry.requestMatchers(HttpMethod.POST, "/api/v1/users/**").permitAll();
                    registry.requestMatchers(HttpMethod.GET, "/swagger-ui**/**").permitAll();
                    registry.requestMatchers(HttpMethod.GET, "/v3/api-docs/**").permitAll();
                    registry.anyRequest().authenticated();
                })
                .addFilterBefore(firebaseTokenAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
