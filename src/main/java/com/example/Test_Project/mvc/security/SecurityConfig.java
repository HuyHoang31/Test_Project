//package com.example.Test_Project.mvc.security;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(authz -> authz
//                        .requestMatchers("/user/**").hasRole("USER")  // Chỉ cho phép người dùng với vai trò USER truy cập
//                        .requestMatchers("/admin/**").hasRole("ADMIN") // Chỉ cho phép người dùng với vai trò ADMIN truy cập
//                        .anyRequest().authenticated() // Tất cả các yêu cầu khác phải được xác thực
//                )
//                .formLogin(form -> form
//                        .loginPage("/login") // Trang đăng nhập tùy chỉnh
//                        .permitAll()
//                        .defaultSuccessUrl("/movies/user/HomePage") // Trang chuyển hướng sau khi đăng nhập thành công
//                        .failureUrl("/login?error=true") // Trang chuyển hướng nếu đăng nhập thất bại
//                )
//                .logout(logout -> logout
//                        .permitAll()
//                );
//        return http.build();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder(); // Sử dụng BCrypt để mã hóa mật khẩu
//    }
//
//    @Bean
//    public UserDetailsService userDetailsService() throws Exception {
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withUsername("user")
//                .password(passwordEncoder().encode("password")) // Mật khẩu cho user
//                .roles("USER") // Vai trò USER
//                .build());
//        manager.createUser(User.withUsername("admin")
//                .password(passwordEncoder().encode("admin")) // Mật khẩu cho admin
//                .roles("ADMIN") // Vai trò ADMIN
//                .build());
//        return manager;
//    }
//}
