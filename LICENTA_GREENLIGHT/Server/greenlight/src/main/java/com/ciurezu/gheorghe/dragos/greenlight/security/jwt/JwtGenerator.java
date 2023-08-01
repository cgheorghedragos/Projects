package com.ciurezu.gheorghe.dragos.greenlight.security.jwt;

import com.auth0.jwt.JWT;
import com.ciurezu.gheorghe.dragos.greenlight.security.SecurityConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;


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
