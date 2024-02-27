package org.maxym.spring.springbootsecurity1.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.maxym.spring.springbootsecurity1.security.JWTUtil;
import org.maxym.spring.springbootsecurity1.service.PersonDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

import static java.util.Objects.nonNull;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;
    private final PersonDetailsService personDetailsService;

    @Autowired
    public JwtTokenFilter(JWTUtil jwtUtil, PersonDetailsService personDetailsService) {
        this.jwtUtil = jwtUtil;
        this.personDetailsService = personDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        final String authorizationHeader = request.getHeader("Authorization");


        if (nonNull(authorizationHeader) && authorizationHeader.startsWith("Bearer ")) {
            String jwtToken = authorizationHeader.substring(7);
            String username = jwtUtil.extractUsername(jwtToken);

            UserDetails userDetails = personDetailsService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,
                    userDetails.getPassword(),
                    userDetails.getAuthorities());

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                if (jwtUtil.validateToken(jwtToken, username)) {
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}
