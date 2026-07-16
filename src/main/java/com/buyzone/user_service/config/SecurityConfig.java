package com.buyzone.user_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http){

        http
                .csrf(
                        csrf -> csrf.disable()
                );

        http
                .authorizeHttpRequests(
                        auth -> auth
                                .requestMatchers(HttpMethod.GET , "/").permitAll()
                                .requestMatchers(HttpMethod.POST, "/").hasAnyRole("USER", "ADMIN" , "GUEST")
                                .requestMatchers(HttpMethod.POST, "/api/users/adduser").permitAll()
                                .requestMatchers(HttpMethod.DELETE).hasAnyRole("ADMIN")
                                .anyRequest().authenticated()

                );

        http.httpBasic(Customizer.withDefaults());



        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            UserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);

        return new ProviderManager(daoAuthenticationProvider);
    }

}
