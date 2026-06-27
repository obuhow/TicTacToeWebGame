package com.faraldma.tictactoe.security;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class AuthFilter extends GenericFilterBean {
    private final AuthService authService;
    public final static List<String> PUBLIC_URLS = List.of("/auth/register", "/swagger-ui", "/v3/api-docs", "/error");


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String uri = httpRequest.getRequestURI();
        if (PUBLIC_URLS.stream().anyMatch(uri::startsWith)) {
            chain.doFilter(request, response);
            return;
        }

        String authHeader = httpRequest.getHeader("Authorization");
        
        if (authHeader == null || !authHeader.startsWith("Basic ")) {
           httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
           httpResponse.setContentType("application/json");
           httpResponse.getWriter().write("Wrong authorization header");
           return;
        }

        try {
            Authentication auth = authService.authenticate(authHeader);
            SecurityContextHolder.getContext().setAuthentication(auth);
            chain.doFilter(request, response);
        } catch (Exception e) {
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpResponse.setContentType("application/json");
            httpResponse.getWriter().write("Wrong authorization header :" + e.getMessage());
        } 
    }
}