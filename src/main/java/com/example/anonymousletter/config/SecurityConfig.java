package com.example.anonymousletter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Cho phép truy cập các trang public
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/", "/home",          // Trang chủ
                                "/login", "/register", // Form login & đăng ký
                                "/h2-console/**",      // H2 Database console
                                "/css/**", "/js/**", "/images/**"
                        ).permitAll()
                        .anyRequest().authenticated() // Còn lại phải đăng nhập
                )

                .formLogin(form -> form
                        .loginPage("/login")              // Hiển thị form login (GET)
                        .loginProcessingUrl("/process-login")  // URL form gửi POST để xác thực
                        .defaultSuccessUrl("/home", true)
                        .failureUrl("/login?error=true")
                        .permitAll()
                )

                // Cấu hình logout
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout=true")
                        .permitAll()
                )

                // CSRF — tắt hoàn toàn để tránh lỗi vòng lặp login (chỉ tạm thời trong dev)
                .csrf(csrf -> csrf.disable())

                // Cho phép nhúng H2 console trong iframe
                .headers(headers -> headers.frameOptions(frame -> frame.disable()));

        return http.build();
    }
}
