package com.example.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    private CareerCraftersHubUserDetailsService careerCraftersHubUserDetailsService;
    private JwtUtil jwtUtil;

    @Autowired
    public JwtRequestFilter(CareerCraftersHubUserDetailsService careerCraftersHubUserDetailsService, JwtUtil jwtUtil) {
        this.careerCraftersHubUserDetailsService = careerCraftersHubUserDetailsService;
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        UserDetailsImpl userDetails = careerCraftersHubUserDetailsService.extractUserDetailsFromRequest(request);
        if (userDetails != null) {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
        filterChain.doFilter(request, response);
    }
}