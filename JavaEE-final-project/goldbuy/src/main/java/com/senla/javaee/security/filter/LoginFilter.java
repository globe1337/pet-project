package com.senla.javaee.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.senla.javaee.dto.credentials.CredentialsInfoDto;
import com.senla.javaee.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    @Autowired
    private JwtProvider jwtProvider;

    private final ObjectMapper mapper;

    public LoginFilter(AuthenticationManager authenticationManager, ObjectMapper objectMapper) {
        super(authenticationManager);
        this.mapper = objectMapper;
    }

    @Override
    protected boolean requiresAuthentication(HttpServletRequest request, HttpServletResponse response) {
        return super.requiresAuthentication(request, response);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        CredentialsInfoDto credentialsInfoDto = null;
        try {

            credentialsInfoDto = mapper.readValue(request.getInputStream(), CredentialsInfoDto.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(
                        credentialsInfoDto.getLogin(),
                        credentialsInfoDto.getPassword()
                )
        );
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) {
        String token = prepareJwt(authResult);
        response.addHeader(HttpHeaders.AUTHORIZATION, token);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.getOutputStream().println("ERROR \nAUTHENTICATION ERROR \nwrong login or password");
    }

    private String prepareJwt(Authentication authResult) {
        return jwtProvider.buildToken(authResult.getName());
    }
}
