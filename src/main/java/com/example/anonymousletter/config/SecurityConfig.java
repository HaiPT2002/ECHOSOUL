package com.example.anonymousletter.config;

import com.example.anonymousletter.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    // 1️⃣ Mã hóa mật khẩu
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 2️⃣ Provider xác thực bằng userDetailsService
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    // 3️⃣ AuthenticationManager dùng để quản lý quá trình đăng nhập
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    // 4️⃣ Cấu hình quyền truy cập & form login
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authenticationProvider(authenticationProvider())

                // Quyền truy cập các trang
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/", "/home",
                                "/login", "/register",
                                "/h2-console/**",
                                "/css/**", "/js/**", "/images/**"
                        ).permitAll()
                        .anyRequest().authenticated()
                )

                // Form login
                .formLogin(form -> form
                        .loginPage("/login")                   // GET /login để hiển thị form
                        .loginProcessingUrl("/process-login")  // POST /process-login để xác thực
                        .defaultSuccessUrl("/home", true)      // Sau khi đăng nhập thành công
                        .failureUrl("/login?error=true")       // Sai mật khẩu → báo lỗi
                        .permitAll()
                )

                // Logout
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout=true")
                        .permitAll()
                )

                // Dev mode: tắt CSRF và cho phép H2 console
                .csrf(csrf -> csrf.disable())
                .headers(headers -> headers.frameOptions(frame -> frame.disable()));

        return http.build();
    }
}



//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                // Cho phép truy cập các trang public
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers(
//                                "/", "/home",          // Trang chủ
//                                "/login", "/register", // Form login & đăng ký
//                                "/h2-console/**",      // H2 Database console
//                                "/css/**", "/js/**", "/images/**"
//                        ).permitAll()
//                        .anyRequest().authenticated() // Còn lại phải đăng nhập
//                )
//
//                .formLogin(form -> form
//                        .loginPage("/login")              // Hiển thị form login (GET)
//                        .loginProcessingUrl("/process-login")  // URL form gửi POST để xác thực
//                        .defaultSuccessUrl("/home")
//                        .failureUrl("/login?error=true")
//                        .permitAll()
//                )
//
//                // Cấu hình logout
//                .logout(logout -> logout
//                        .logoutUrl("/logout")
//                        .logoutSuccessUrl("/login?logout=true")
//                        .permitAll()
//                )
//
//                // Cho phép nhúng H2 console trong iframe
//                .headers(headers -> headers.frameOptions(frame -> frame.disable()));
//
//        return http.build();
//    }
//}
