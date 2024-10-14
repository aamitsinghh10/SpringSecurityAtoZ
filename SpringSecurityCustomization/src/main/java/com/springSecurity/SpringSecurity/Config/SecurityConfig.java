package com.springSecurity.SpringSecurity.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/welcome").permitAll() // Public endpoint
                        .requestMatchers("/admin").hasRole("ADMIN") // Protected endpoint for admin
                        .requestMatchers("/user").hasRole("USER") // Protected endpoint for user
                        .anyRequest().authenticated() // All other requests require authentication
                )
                .formLogin(form -> form
                        .loginPage("/login") // Custom login page
                        .permitAll() // Allow all users to access the login page
                )
                .logout(logout -> logout.permitAll()); // Allow all users to log out

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("admin123")
                .roles("ADMIN")
                .build();
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("user123")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(admin, user);
    }
}
