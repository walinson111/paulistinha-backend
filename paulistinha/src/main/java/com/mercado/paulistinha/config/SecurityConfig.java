package com.mercado.paulistinha.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.mercado.paulistinha.service.FuncionarioUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final FuncionarioUserDetailsService userDetailsService;

    public SecurityConfig(FuncionarioUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.GET, "/funcionarios/**").hasRole("GERENTE")
                .requestMatchers(HttpMethod.POST, "/funcionarios/**").hasRole("GERENTE")
                .requestMatchers(HttpMethod.PUT, "/funcionarios/**").hasRole("GERENTE")
                .requestMatchers(HttpMethod.DELETE, "/funcionarios/**").hasRole("GERENTE")

                .anyRequest().authenticated()
            )
            .userDetailsService(userDetailsService)
            .httpBasic();

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}


