package com.the_dapda.global.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfiguration {

    // AuthenticationManager 빈 등록
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // CSRF 보호 비활성화 (필요한 경우 활성화 가능)
                .formLogin(formLogin -> formLogin
                        .loginPage("/login") // 커스텀 로그인 페이지 경로
//                        .loginProcessingUrl("/login") // 해당 설정은 security가 url을 가로채 자동으로 인증을 수행함
                        .defaultSuccessUrl("/diaries/main")
                        .usernameParameter("id")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout") // 로그아웃 URL 설정
                        .logoutSuccessHandler((request, response, authentication) -> {
                            if (authentication != null) {
                                System.out.println("로그아웃 성공: " + authentication.getName());
                            } else {
                                System.out.println("로그인 상태가 아님");
                            }
                            response.sendRedirect("/login?logout");
                        })
                )

                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) // 세션이 필요할 때 생성
                )
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/css/**", "/js/**", "/images/**", "/register", "/login", "/logout", "login.html").permitAll() // 정적 리소스 및 특정 URL 접근 허용
                        .anyRequest().authenticated() // 나머지 요청은 인증 필요
                )
                .exceptionHandling(exception -> exception
                        .accessDeniedHandler(new CustomAccessDeniedHandler())
                        .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                );

        return http.build();
    }

}