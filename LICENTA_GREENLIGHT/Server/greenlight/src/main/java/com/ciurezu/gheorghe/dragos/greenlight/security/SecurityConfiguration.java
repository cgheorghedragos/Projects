package com.ciurezu.gheorghe.dragos.greenlight.security;

import com.ciurezu.gheorghe.dragos.greenlight.data.model.StandardCategories;
import com.ciurezu.gheorghe.dragos.greenlight.data.model.StandardRoles;
import com.ciurezu.gheorghe.dragos.greenlight.security.filter.GreenLightAuthenticationFilter;
import com.ciurezu.gheorghe.dragos.greenlight.security.filter.GreenLightAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import static com.ciurezu.gheorghe.dragos.greenlight.security.GreenLightDSL.greenLightDsl;
import static org.springframework.http.HttpMethod.GET;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests().antMatchers("/api/v1/faq/add-faq").hasAuthority(StandardRoles.ADMIN);
        http.authorizeRequests().antMatchers("/api/v1/trash_classifier/add-trash-category").hasAuthority(StandardRoles.ADMIN);
        http.authorizeRequests().antMatchers("/api/v1/trash_classifier/delete-trash-category").hasAuthority(StandardRoles.ADMIN);
        http.authorizeRequests().antMatchers("/api/v1/faq/delete-faq").hasAuthority(StandardRoles.ADMIN);
        http.authorizeRequests().antMatchers("/api/v1/trash_classifier/edit-trash-category").hasAuthority(StandardRoles.ADMIN);
        http.authorizeRequests().antMatchers("/api/v1/faq/edit-faq").hasAuthority(StandardRoles.ADMIN);
        http.authorizeRequests().antMatchers("/api/v1/voucher/add-voucher").hasAuthority(StandardRoles.SHOPPER);
        http.authorizeRequests().anyRequest().permitAll();
        http.apply(greenLightDsl());
        http.addFilterBefore(new GreenLightAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
