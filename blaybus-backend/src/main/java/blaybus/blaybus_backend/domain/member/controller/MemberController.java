package blaybus.blaybus_backend.domain.member.controller;

import blaybus.blaybus_backend.domain.member.dto.InfoResponseDTO;
import blaybus.blaybus_backend.domain.member.dto.UpdatePwRequestDTO;
import blaybus.blaybus_backend.domain.member.dto.UpdatePwResponseDTO;
import blaybus.blaybus_backend.domain.member.service.MemberService;
import blaybus.blaybus_backend.global.common.SessionManager;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/members")
@RestController
public class MemberController {

    private final MemberService memberService;
    private final SessionManager sessionManager;

    @Autowired
    public MemberController(MemberService memberService, SessionManager sessionManager) {
        this.memberService = memberService;
        this.sessionManager = sessionManager;
    }

    @Operation(summary = "내 정보 확인", description = "Header의 세션 ID로 사용자 인증")
    @GetMapping("/info")
    public ResponseEntity<InfoResponseDTO> getInfo(HttpSession session) {
        Long id = sessionManager.getMemberId(session);
        InfoResponseDTO memberInfo = memberService.getInfoById(id);
        return ResponseEntity.ok(memberInfo);
    }

    @Operation(summary = "패스워드 변경", description = "Header의 세션 ID로 사용자 인증")
    @PutMapping("/update-pw")
    public ResponseEntity<UpdatePwResponseDTO> updatePw(HttpSession session,
            @RequestBody UpdatePwRequestDTO updateRequest) {
        Long id = sessionManager.getMemberId(session);
        UpdatePwResponseDTO updateResponse = memberService.updatePw(id, updateRequest);
        return ResponseEntity.ok(updateResponse);
    }

}
