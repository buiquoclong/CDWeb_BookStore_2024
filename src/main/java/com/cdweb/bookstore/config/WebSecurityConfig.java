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
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestResolver;
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

    @Autowired
    private ClientRegistrationRepository clientRegistrationRepository;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // vô hiệu hóa tính năng bảo vệ CSRF
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/thanh-toan", "/gio-hang").authenticated() // cần xác thực tài khoản
                        .requestMatchers("/admin-page/**").hasRole("ADMIN") // phân quyền cho admin
                        .anyRequest().permitAll())
                .formLogin(form -> form
                        .loginPage("/dang-nhap")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/") // login success
                        .failureUrl("/dang-nhap?error=true") // login failed
                        .usernameParameter("email")    // username
                        .passwordParameter("password")) // password
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")) // logout success
                .oauth2Login(oauth2 -> oauth2 // login google
                        .loginPage("/dang-nhap")
                        .authorizationEndpoint(authorization -> authorization // set up url default google
                                .authorizationRequestResolver(customOAuth2AuthorizationRequestResolver()))
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(oAuth2UserService)) // lấy thông tin người dùng từ google
                        .successHandler(new AuthenticationSuccessHandler() { // xử lý khi login google success
                            @Override
                            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                                CustomOAuth2User oauthUser = (CustomOAuth2User) authentication.getPrincipal(); // trả về đại diện đã xác thực (thông tin)
                                String email = oauthUser.getAttribute("email");
                                String username = oauthUser.getAttribute("name");

                                // Kiểm tra và xử lý tài khoản Google đăng nhập
                                userService.processOAuthPostLogin(email, username);
                                response.sendRedirect("/");
                            }
                        }));


        return http.build();
    }

    @Bean
    public AuthenticationSuccessHandler savedRequestAwareAuthenticationSuccessHandler() {
        return new SavedRequestAwareAuthenticationSuccessHandler();
    }

    public OAuth2AuthorizationRequestResolver customOAuth2AuthorizationRequestResolver() {
        return new CustomAuthorizationRequestResolver(clientRegistrationRepository);
    }

    // xác thực người dùng
    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoderConfig.encoder());
        return authProvider;
    }
}
