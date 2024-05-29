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
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

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
        http.csrf(csrf -> csrf.disable());

        http.authorizeHttpRequests(authz -> authz
                .requestMatchers("/thanh-toan", "/gio-hang").authenticated()
                .requestMatchers("/admin-page/**").hasRole("ADMIN")
                .anyRequest().permitAll()
        );

        http.formLogin(form -> form
                .loginPage("/dang-nhap")
                .loginProcessingUrl("/login")
                .successHandler(savedRequestAwareAuthenticationSuccessHandler())
                .failureUrl("/dang-nhap?error=true")
                .usernameParameter("email")
                .passwordParameter("password")
        );

        http.logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
        );

        http.oauth2Login(oauth2 -> oauth2
                .loginPage("/dang-nhap")
                .userInfoEndpoint(userInfo -> userInfo.userService(oAuth2UserService))
                .successHandler(new CustomAuthenticationSuccessHandler(userService))
        );

        http.authenticationProvider(authProvider());
        return http.build();
    }

    @Bean
    public AuthenticationSuccessHandler savedRequestAwareAuthenticationSuccessHandler() {
        return new SavedRequestAwareAuthenticationSuccessHandler();
    }

    private static class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

        private final IUserService userService;

        public CustomAuthenticationSuccessHandler(IUserService userService) {
            this.userService = userService;
        }

        @Override
        public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
            CustomOAuth2User oauthUser = (CustomOAuth2User) authentication.getPrincipal();
            String email = oauthUser.getAttribute("email");
            String username = oauthUser.getAttribute("name");

            // Kiểm tra và xử lý tài khoản Google đăng nhập
            userService.processOAuthPostLogin(email, username);
            // Chuyển hướng người dùng tới URL trước khi đăng nhập
            new SavedRequestAwareAuthenticationSuccessHandler().onAuthenticationSuccess(request, response, authentication);
        }
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoderConfig.encoder());
        return authProvider;
    }
}
