package com.pizza.pizzeria.web.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
       http
                .csrf().disable()
                .cors().and()
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.GET, "/api/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/pizzas/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT).hasRole("ADMIN")
                .requestMatchers("/api/orders/random").hasAuthority("random_order")
                .requestMatchers("/api/users/**").hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
        
        return http.build();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration  authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}

