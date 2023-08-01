package com.ciurezu.gheorghe.dragos.greenlight.security;

import com.ciurezu.gheorghe.dragos.greenlight.security.filter.GreenLightAuthenticationFilter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

public class GreenLightDSL extends AbstractHttpConfigurer<GreenLightDSL, HttpSecurity> {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
        GreenLightAuthenticationFilter greenLightAuthenticationFilter = new GreenLightAuthenticationFilter(authenticationManager);
        greenLightAuthenticationFilter.setFilterProcessesUrl("/api/login");
        http.addFilter(greenLightAuthenticationFilter);
    }

    public static GreenLightDSL greenLightDsl() {
        return new GreenLightDSL();
    }
}
