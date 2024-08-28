package com.ttknpdev.understandlineloginintegratewithwebapp.config.secure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class LineLoginSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests((authenticate) ->
                        authenticate
                                .requestMatchers("/api/hello-word").authenticated()
                                .requestMatchers("/api/app").authenticated()
                                .requestMatchers("/api/app/info-oauth").authenticated()
                                .requestMatchers("/api/logout").permitAll()
                                )
                .oauth2Login(withDefaults()); // for allowed oauth2 render to line login template
        return httpSecurity.build();
    }
}
