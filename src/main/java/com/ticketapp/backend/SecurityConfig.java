package com.ticketapp.backend;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // 1. Disable CSRF so our simple HTML form works
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated() // 2. Lock every single URL
                )
                .httpBasic(Customizer.withDefaults()) // 3. Enable Pop-up Login
                .formLogin(Customizer.withDefaults()); // 4. Enable Standard Login Form

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        // This replaces the application.properties user settings!
        UserDetails admin = User.withDefaultPasswordEncoder() // Using default encoder for demo
                .username("admin")
                .password("password123")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(admin);
    }
}