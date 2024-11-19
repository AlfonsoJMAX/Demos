package com.example.taskmanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        
        manager.createUser(User.withUsername("admin")
                .password(passwordEncoder().encode("admin")) // Usa el PasswordEncoder
                .roles("ADMIN")
                .build());

        manager.createUser(User.withUsername("user")
                .password(passwordEncoder().encode("password")) // Usa el PasswordEncoder
                .roles("USER")
                .build());
        
        return manager;
    }
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
 
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {    	
    	http
            .csrf().disable()
            .authorizeRequests()
            // Permitir acceso a la consola H2
            .requestMatchers("/h2-console/**").permitAll()
            .requestMatchers("/task/**").hasAnyRole("USER", "ADMIN")
            .requestMatchers("/task/{id}/delete").hasRole("ADMIN")
            .anyRequest().authenticated() 
            .and()
            // Necesario para permitir el iframe en la consola H2
            .headers().frameOptions().sameOrigin()
            .and()
            .httpBasic();
        return http.build();
    }
}
