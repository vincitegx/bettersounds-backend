package com.bettersounds.config;

import com.bettersounds.security.JwtAuthFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, proxyTargetClass = true)
public class SecurityConfig{
    
    private final JwtAuthFilter jwtAuthFilter;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.cors().and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/resources/**", "/webjars/**", "/assests/**").permitAll()
                .antMatchers("/api/auth/**").permitAll()
                .antMatchers(HttpMethod.GET,"/api/beat/**").permitAll()
                .antMatchers(HttpMethod.GET,"/api/beatkey/**").permitAll()
                .antMatchers(HttpMethod.GET,"/api/comments/**").permitAll()
                .antMatchers(HttpMethod.GET,"/api/genres/**").permitAll()
                .antMatchers(HttpMethod.GET,"/api/mood/**").permitAll()
                .antMatchers(HttpMethod.GET,"/api/reactions/**").permitAll()
                .antMatchers(HttpMethod.GET,"/api/orders/**").permitAll()
                .anyRequest()
                .authenticated();
        httpSecurity.addFilterBefore(jwtAuthFilter,
                UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
