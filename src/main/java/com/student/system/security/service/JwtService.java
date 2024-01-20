package com.student.system.security.service;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.function.Function;

public interface JwtService {
    String generateToken(UserDetails userDetails);

    String generateRefreshToken(UserDetails userDetails);

    boolean validateToken(String token, UserDetails userDetails);

    String extractUserName(String token);

    <T> T extractClaim(String token, Function<Claims, T> claimsResolver);

    Claims extractAllClaims(String token);

    Key getSignKey();
}
