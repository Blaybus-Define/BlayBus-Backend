package blaybus.blaybus_backend.global.security;

import blaybus.blaybus_backend.domain.auth.exception.AuthException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
@RequiredArgsConstructor
public class CustomSessionFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        System.out.println(" =------");
        String sessionId = request.getHeader("Authorization");
        if (sessionId != null) {
            HttpSession session = request.getSession(false); // 기존 세션만 가져옴 (없으면 null 반환)
            if (session != null && sessionId.equals(session.getId())) {
                SecurityContext context = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");

                if (context != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    SecurityContextHolder.setContext(context);
                }
            }
        }
        chain.doFilter(request, response);

    }
}
