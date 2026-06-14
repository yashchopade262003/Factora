package com.factoryflow.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.factoryflow.auth.jwtUtils.JwtAuthenticationFilter;

@Configuration
public class SecurityConfigure {

    @Autowired
    private JwtAuthenticationFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(
            HttpSecurity httpSecurity)
            throws Exception {

        httpSecurity

                .csrf(csrf -> csrf.disable())

                // VERY IMPORTANT
                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS))

                .authorizeHttpRequests(auth -> auth

                        .requestMatchers(
                        		"/auth/login",
                                "/user/register",
                                "/vendors/register",
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/swagger-ui.html")
                        .permitAll()

                        .anyRequest()
                        .authenticated())

                // IMPORTANT
                .addFilterBefore(
                        jwtFilter,
                        UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }
}