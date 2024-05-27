package com.cdweb.bookstore.config;

import com.cdweb.bookstore.oauth2.CustomOAuth2User;
import com.cdweb.bookstore.service.IUserService;
import com.cdweb.bookstore.service.impl.CustomOAuth2UserServiceImp;
import com.cdweb.bookstore.service.impl.UserDetailServiceImp;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

@Configuration
public class WebSecurityConfig {
    @Autowired
    private UserDetailServiceImp userDetailsService;

    @Autowired
    private CustomOAuth2UserServiceImp oAuth2UserService;

    @Autowired
    private IUserService userService;

    @Autowired
    private PasswordEncoderConfig passwordEncoderConfig;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests(authz -> authz
                        .requestMatchers("/thanh-toan", "/gio-hang").authenticated()
                        .requestMatchers("/admin-page/**").hasRole("ADMIN")
                        .anyRequest().permitAll())
                .formLogin()
                .loginPage("/dang-nhap")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/")
                .failureUrl("/dang-nhap?error=true")
                .usernameParameter("email")
                .passwordParameter("password")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/");
        http.authenticationProvider(authProvider());
        return http.build();
    }
    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoderConfig.encoder());
        return authProvider;
    }
}
