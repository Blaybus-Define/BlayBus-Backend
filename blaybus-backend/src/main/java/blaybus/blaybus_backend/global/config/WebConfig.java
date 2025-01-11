package blaybus.blaybus_backend.global.config;

import blaybus.blaybus_backend.domain.auth.controller.AuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthInterceptor())
                .addPathPatterns("/**") // 모든 경로에 인터셉터 적용
                .excludePathPatterns(
                        "/auth/login",
                        "/auth/logout",
                        "/auth/signup",
                        "/public/**",
                        "/error",
                        "/swagger-ui/**",
                        "/swagger-resources/**",
                        "/swagger/**",
                        "/v3/api-docs/**"); // 인증 제외 경로
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 모든 경로에 대해 CORS 설정
                .allowedOrigins("http://localhost:3000") // 허용할 도메인
                .allowedMethods("GET", "POST", "PUT", "DELETE") // 허용할 메서드
                .allowedHeaders("*") // 모든 헤더 허용
                .allowCredentials(true) // 쿠키 허용
                .maxAge(3600); // 캐싱 시간 (초)
    }
}
