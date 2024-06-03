package com.example.Tap.security;

import com.example.Tap.security.filter.AuthenticatonFilter;
import com.example.Tap.security.handler.SuccessfullyHandler;
import com.example.Tap.security.handler.UnsuccessfullyHandler;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

public class DSL extends AbstractHttpConfigurer<DSL, HttpSecurity> {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
        AuthenticatonFilter authFilter = new AuthenticatonFilter(authenticationManager);
        authFilter.setFilterProcessesUrl("/api/login");
        authFilter.setAuthenticationSuccessHandler(new SuccessfullyHandler());
        authFilter.setAuthenticationFailureHandler(new UnsuccessfullyHandler());
        http.addFilter(authFilter);
    }

    public static DSL dsl() {
        return new DSL();
    }
}
