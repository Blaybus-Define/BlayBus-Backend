package blaybus.blaybus_backend.global.config;

import blaybus.blaybus_backend.domain.auth.controller.AuthInterceptor;
import org.springframework.context.annotation.Configuration;
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
}
