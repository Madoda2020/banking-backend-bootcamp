package com.emkay.bank.banking_platform.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Profile("dev")
public class DevSecurityConfig {

    @Bean
    SecurityFilterChain devSecurityFilterChain(HttpSecurity http) throws Exception{
        return http.authorizeHttpRequests(auth -> auth
                .requestMatchers(PathRequest.toH2Console()).permitAll()
                .requestMatchers("/api/**").permitAll()
                        .requestMatchers("/actuator/health").permitAll()
                .anyRequest().authenticated())
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers(PathRequest.toH2Console()
                        ,request -> request.getRequestURI().startsWith("/api/")))
                .headers(headers -> headers
                        .frameOptions(frame -> frame.sameOrigin()))
                .build();
    }


}
