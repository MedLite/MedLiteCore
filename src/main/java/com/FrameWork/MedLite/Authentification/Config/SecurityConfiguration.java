/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Authentification.Config;

import com.FrameWork.MedLite.Authentification.Config.jwt.JwtAuthenticationEntryPoint;
import com.FrameWork.MedLite.Authentification.Config.jwt.JwtRequestFilter;
import com.FrameWork.MedLite.Authentification.Config.jwt.NotFound; 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 *
 * @author Administrator
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    
    private final Logger log = LoggerFactory.getLogger(SecurityConfiguration.class);
    
    private final Environment env;
    
    public SecurityConfiguration(Environment env) {
        this.env = env;
    }
    
    @Autowired
    private AuthenticationProvider authenticationProvider;
    
    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    
    @Autowired
    private JwtRequestFilter jwtRequestFilter;
    
        @Autowired
    private NotFound notFound;
    
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // CSRF Protection (Consider if you REALLY need to disable it)
                .csrf(csrf -> csrf.disable())
                // Authorization Configuration
                .authorizeHttpRequests(requests -> requests
                .requestMatchers("/api/auth/login").permitAll()
                .requestMatchers("/api/auth/signup").permitAll()
                .requestMatchers("/api/**").authenticated()
                ).exceptionHandling()
                .authenticationEntryPoint(notFound).and()
                .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        
        return http.build();
    }
    
}
