package org.maxym.spring.springbootsecurity1.config;

import org.maxym.spring.springbootsecurity1.service.PersonDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final PersonDetailsService personDetailsService;
    private final JwtTokenFilter filter;

    @Autowired
    public SecurityConfig(PersonDetailsService personDetailsService, JwtTokenFilter filter) {
        this.personDetailsService = personDetailsService;
        this.filter = filter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(configurer ->
                        configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .userDetailsService(personDetailsService)
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/auth/login", "/auth/registration", "/error").permitAll()
                        .anyRequest().hasAnyRole("USER", "ADMIN")
                )
                .formLogin(form -> form
                        .loginPage("/auth/login")
                        .loginProcessingUrl("/process_login")
                        .defaultSuccessUrl("/hello", true)
                        .failureUrl("/auth/login?error")
                )
                .logout(configurer -> configurer
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/auth/login")
                )
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class).build();
    }
}
