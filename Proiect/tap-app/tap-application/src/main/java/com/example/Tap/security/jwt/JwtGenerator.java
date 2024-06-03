package com.example.Tap.security.jwt;

import com.auth0.jwt.JWT;
import com.example.Tap.security.SecurityConstants;

import java.util.Date;
import java.util.List;

public class JwtGenerator {
    public String generateAccessToken(String username, String requestURL, List<String> roles) {
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.JWT_EXPIRATION_TIME))
                .withIssuer(requestURL)
                .withClaim(SecurityConstants.JWT_CUSTOM_CLAIM, roles)
                .sign(SecurityConstants.ALGORITHM);
    }

    public String generateRefreshToken(String username, String requestURL) {
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.JWT_EXPIRATION_TIME))
                .withIssuer(requestURL)
                .sign(SecurityConstants.ALGORITHM);
    }
}
