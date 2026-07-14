package com.buyzone.user_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)  {

        http
                .csrf(
                        csrf -> csrf.disable()
                );

        // In the code below we are permitting test API completely to get auto authorized or to skip the part of authorization
//        http
//            .authorizeHttpRequests(
//                    auth -> auth.requestMatchers("/test").permitAll()
//                            .anyRequest().authenticated()
//            );


//     But the code below is also the copy of the code above only it doesn't have the part where we are using request matcher to permit to test
        http
                .authorizeHttpRequests(
                        auth -> auth
                                .requestMatchers(HttpMethod.GET , "/").permitAll()
                                .anyRequest().authenticated()
                );

        http.httpBasic(Customizer.withDefaults());



        return http.build();
    }

}
