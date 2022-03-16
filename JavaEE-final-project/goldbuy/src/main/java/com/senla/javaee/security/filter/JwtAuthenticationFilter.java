package com.senla.javaee.security.filter;


import com.senla.javaee.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.util.Optional.ofNullable;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    public static final String BEARER = "Bearer";
    private final UserDetailsService userDetailService;
    @Autowired
    private JwtProvider jwtProvider;

    public JwtAuthenticationFilter(UserDetailsService userDetailService) {

        this.userDetailService = userDetailService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        final String authorization = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorization != null && authorization.startsWith(BEARER)) {
            final String token = authorization.substring(BEARER.length());
            final String username = jwtProvider.getUsernameFromToken(token);
            ofNullable(userDetailService.loadUserByUsername(username))
                    .ifPresent(
                            x -> SecurityContextHolder.getContext().setAuthentication(
                                    new UsernamePasswordAuthenticationToken(x, null, x.getAuthorities()//todo
                                    )
                            )
                    );
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);

    }
}


