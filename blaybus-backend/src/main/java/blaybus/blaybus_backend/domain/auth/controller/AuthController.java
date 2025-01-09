package blaybus.blaybus_backend.domain.auth.controller;

import blaybus.blaybus_backend.domain.auth.service.AuthService;
import blaybus.blaybus_backend.domain.auth.dto.LoginRequest;
import blaybus.blaybus_backend.domain.auth.dto.LogoutResponse;
import blaybus.blaybus_backend.domain.auth.dto.SignupRequest;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest, HttpSession session) {
        return ResponseEntity.ok(authService.login(loginRequest, session));
    }

    //개발용 회원가입 api
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest signupRequest) {
        return ResponseEntity.ok(authService.signup(signupRequest));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok(new LogoutResponse("로그아웃 성공"));
    }
}
