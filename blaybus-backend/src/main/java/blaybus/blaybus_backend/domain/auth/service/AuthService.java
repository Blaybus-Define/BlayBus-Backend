package blaybus.blaybus_backend.domain.auth.service;

import blaybus.blaybus_backend.domain.auth.exception.AuthException;
import blaybus.blaybus_backend.domain.auth.dto.LoginRequest;
import blaybus.blaybus_backend.domain.auth.dto.LoginResponse;
import blaybus.blaybus_backend.domain.auth.dto.SignupRequest;
import blaybus.blaybus_backend.domain.member.entity.Member;
import blaybus.blaybus_backend.domain.member.exception.MemberException;
import blaybus.blaybus_backend.domain.member.repository.MemberRepository;
import blaybus.blaybus_backend.global.security.CustomUserDetailsService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {

    private final CustomUserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;

    public LoginResponse login(LoginRequest loginRequest, HttpSession session) {
        // 사용자 인증
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmployeeNumber());

        if (!passwordEncoder.matches(loginRequest.getPassword(), userDetails.getPassword())) {
            throw new AuthException("틀린 비밀번호입니다.");
        }

        // 인증 정보를 SecurityContext에 저장
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 세션에 SecurityContext 저장
        session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
        return new LoginResponse(session.getId());
    }

    public Object signup(SignupRequest signupRequest) {
        // 사번 중복 검사
            if (memberRepository.existsByEmployeeNumber(signupRequest.getEmployeeNumber())) {
                throw new MemberException("이미 존재하는 사번입니다.");
        }

        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(signupRequest.getPassword());

        // 새로운 회원 생성
        Member member = signupRequest.toMember(encodedPassword);

        return memberRepository.save(member);
    }
}
