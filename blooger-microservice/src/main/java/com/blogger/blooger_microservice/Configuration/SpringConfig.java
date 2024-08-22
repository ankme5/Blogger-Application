package com.blogger.blooger_microservice.Configuration;

import com.blogger.blooger_microservice.Exception.AuthenticationFailureEntryPoint;
import com.blogger.blooger_microservice.Filters.JwtVerifyFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SpringConfig {

    @Autowired
    private JwtVerifyFilter jwtVerifyFilter;

    @Autowired
    private AuthenticationFailureEntryPoint authenticationFailureEntryPoint;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(r->r.anyRequest().authenticated())
                .sessionManagement(s->s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtVerifyFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(e->e.authenticationEntryPoint(authenticationFailureEntryPoint));
        return http.build();
    }
}
