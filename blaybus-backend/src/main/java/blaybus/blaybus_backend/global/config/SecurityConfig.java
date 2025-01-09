package blaybus.blaybus_backend.global.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/**", "/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html").permitAll() // 인증 없이 접근 가능
                .requestMatchers("/admin/**").hasRole("ADMIN") // ADMIN 권한만 접근 가능
                .anyRequest().authenticated() // 나머지 요청은 인증 필요
            )
            .logout(logout -> logout
                .logoutUrl("/logout") // 로그아웃 처리 URL
                .logoutSuccessUrl("/") // 로그아웃 성공 후 리다이렉트 경로
                .deleteCookies("JSESSIONID") // 로그아웃 시 쿠키 삭제
                .permitAll()
            );


        return http.build();
    }


}
