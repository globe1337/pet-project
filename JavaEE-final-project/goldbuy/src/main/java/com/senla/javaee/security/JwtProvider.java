package com.senla.javaee.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

@Component
@PropertySource(value = "classpath:application.properties", ignoreResourceNotFound = true)
public class JwtProvider {
    @Value("${expiration}")
    private String secret;
    @Value("${expiration}")
    private String expiration;

    public String buildToken(String username) {
        final Map<String, Object> claims = new HashMap<>();
        claims.put("typ", "acces");
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(Date.from(Instant.now()))
                .setSubject(username)
                .setExpiration(Date.from(Instant.now().plus(Duration.parse(expiration).toMinutes(), ChronoUnit.MINUTES)))
                .signWith(
                        SignatureAlgorithm.HS512,
                        secret.getBytes(StandardCharsets.UTF_8))
                .compact();
    }

    public String getUsernameFromToken(String token) {
        final Claims claims = Jwts.parser()
                .setSigningKey(secret.getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

}
