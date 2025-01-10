package blaybus.blaybus_backend.domain.auth.service;

import blaybus.blaybus_backend.domain.auth.dto.LoginRequest;
import blaybus.blaybus_backend.domain.auth.dto.LoginResponse;
import blaybus.blaybus_backend.domain.auth.dto.SignupRequest;
import blaybus.blaybus_backend.domain.auth.exception.AuthException;
import blaybus.blaybus_backend.domain.member.entity.Member;
import blaybus.blaybus_backend.domain.member.exception.MemberException;
import blaybus.blaybus_backend.domain.member.repository.MemberRepository;
import blaybus.blaybus_backend.global.exception.ErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static blaybus.blaybus_backend.domain.auth.controller.SessionConst.MEMBER_ID;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;

    public LoginResponse login(LoginRequest loginRequest, HttpServletRequest request) {
        Member member = memberRepository.findByLoginId(loginRequest.getLoginId())
                .orElseThrow(() -> new MemberException(ErrorCode.MEMBER_NOT_FOUND));

        if (member != null && member.getPassword().equals(loginRequest.getPassword())) {
            HttpSession session = request.getSession();
            session.setAttribute(MEMBER_ID, member.getId());
            return new LoginResponse();
        } else {
            throw new AuthException(ErrorCode.INVALID_PASSWORD);
        }
    }

    public void signup(SignupRequest signupRequest) {
        if (memberRepository.existsByEmployeeNumber(signupRequest.getEmployeeNumber())) {
            throw new MemberException(ErrorCode.DUPLICATE_EMPLOYEE_NUMBER);
        }
        Member member = signupRequest.toMember();
        memberRepository.save(member);
    }
}
