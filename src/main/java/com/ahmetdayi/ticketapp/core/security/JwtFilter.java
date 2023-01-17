package com.ahmetdayi.ticketapp.core.security;

import com.ahmetdayi.ticketapp.entity.SecurityClient;
import com.ahmetdayi.ticketapp.service.ClientService;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class JwtFilter extends OncePerRequestFilter {


    private final JwtUtil jwtUtil;

    private final ClientService clientService;

    public JwtFilter(JwtUtil jwtUtil, ClientService clientService) {
        this.jwtUtil = jwtUtil;
        this.clientService = clientService;
    }


    @Override

    protected void doFilterInternal
            (
                    HttpServletRequest request,
                    HttpServletResponse response,
                    FilterChain filterChain
            ) throws ServletException, IOException {

        String token = getToken(request);
        String username;

        if (!token.isBlank()) {
            username = jwtUtil.verifyJWT(token).getSubject();
            SecurityClient userDetails = (SecurityClient) clientService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request, response);

    }

    private String getToken(HttpServletRequest request) {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header == null || !header.startsWith("Bearer ")) {
            return "";
        }
        return header.substring(7);
    }
}
