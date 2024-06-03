package com.example.Tap.security.filter;

import com.example.Tap.dto.UserDTO;
import com.example.Tap.security.jwt.JwtGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.stream.Collectors;

@Slf4j
public class AuthenticatonFilter extends UsernamePasswordAuthenticationFilter {
    private final JwtGenerator tokenGenerator;

    private final AuthenticationManager authenticationManager;

    public AuthenticatonFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        this.tokenGenerator = new JwtGenerator();
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        String requestData, username ="", password ="";
        try {
            requestData = request.getReader().lines().collect(Collectors.joining());
            ObjectMapper objectMapper = new ObjectMapper();
            UserDTO userDTO = objectMapper.readValue(requestData, UserDTO.class);
            username = userDTO.getUsername();
            password = userDTO.getPassword();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        log.info("Username is: {}", username);
        log.info("Password is: {}", password);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        return authenticationManager.authenticate(authenticationToken);
    }
}
