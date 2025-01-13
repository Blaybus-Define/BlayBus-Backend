package blaybus.blaybus_backend.domain.member.controller;

import blaybus.blaybus_backend.domain.member.dto.InfoResponseDTO;
import blaybus.blaybus_backend.domain.member.dto.UpdatePwRequestDTO;
import blaybus.blaybus_backend.domain.member.dto.UpdatePwResponseDTO;
import blaybus.blaybus_backend.domain.member.service.MemberService;
import blaybus.blaybus_backend.global.common.SessionManager;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "멤버 API")
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

    @GetMapping("/info")
    public ResponseEntity<InfoResponseDTO> getInfo(HttpSession session) {
        Long id = sessionManager.getMemberId(session);
        InfoResponseDTO memberInfo = memberService.getInfoById(id);
        return ResponseEntity.ok(memberInfo);
    }

    @PutMapping("/update-pw")
    public ResponseEntity<UpdatePwResponseDTO> updatePw(HttpSession session,
            @RequestBody UpdatePwRequestDTO updateRequest) {
        Long id = sessionManager.getMemberId(session);
        UpdatePwResponseDTO updateResponse = memberService.updatePw(id, updateRequest);
        return ResponseEntity.ok(updateResponse);
    }

}
