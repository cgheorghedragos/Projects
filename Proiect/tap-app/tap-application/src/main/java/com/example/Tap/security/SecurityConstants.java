package com.example.Tap.security;

import com.auth0.jwt.algorithms.Algorithm;

public class SecurityConstants {
    public static final int JWT_EXPIRATION_TIME = 60 * 60*60000;
    public static final int JWT_REFRESH_TIME = 60 * 60;
    public static final String JWT_SECRET_CODE = "secret";
    public static final String AUTHORIZATION_TAG = "Bearer ";
    public static final Algorithm ALGORITHM = Algorithm.HMAC256(JWT_SECRET_CODE.getBytes());
    public static final String JWT_CUSTOM_CLAIM = "roles";
    public static final String LOGIN_PATH = "/api/login";
}
