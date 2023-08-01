package com.ciurezu.gheorghe.dragos.greenlight.security.filter;

import com.ciurezu.gheorghe.dragos.greenlight.data.model.shared.UserDTO;
import com.ciurezu.gheorghe.dragos.greenlight.security.jwt.JwtGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
public class GreenLightAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final JwtGenerator tokenGenerator;

    private final AuthenticationManager authenticationManager;

    public GreenLightAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        this.tokenGenerator = new JwtGenerator();
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String requestData, username ="", password ="";
        try {
            requestData = request.getReader().lines().collect(Collectors.joining());
            ObjectMapper objectMapper = new ObjectMapper();
            UserDTO userDTO = objectMapper.readValue(requestData,UserDTO.class);
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

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        User user = (User) authentication.getPrincipal();

        String access_token = tokenGenerator
                .generateAccessToken(user.getUsername(),
                        request.getRequestURL().toString(),
                        user.getAuthorities()
                                .stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()));

        String refresh_token = tokenGenerator
                .generateRefreshToken(user.getUsername(),
                        request.getRequestURL().toString());

        Map<String, String> tokens = new HashMap<>();
        tokens.put("access_token", access_token);
        tokens.put("refresh_token", refresh_token);

        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), tokens);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setContentType(APPLICATION_JSON_VALUE);

        Map<String, String> error = new HashMap<>();

        error.put("error", "User Not Found");
        response.setStatus(HttpStatus.NOT_FOUND.value());
        new ObjectMapper().writeValue(response.getOutputStream(), error);
    }
}
