package blaybus.blaybus_backend.domain.auth.controller;

import blaybus.blaybus_backend.domain.auth.exception.AuthException;
import blaybus.blaybus_backend.global.exception.ErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

import static blaybus.blaybus_backend.domain.auth.controller.SessionConst.MEMBER_ID;

public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute(MEMBER_ID) == null) {
            throw new AuthException(ErrorCode.UNAUTHORIZED_USER);
        }

        return true;
    }
}
