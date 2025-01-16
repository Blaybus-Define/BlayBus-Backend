package blaybus.blaybus_backend.domain.experience.controller;

import blaybus.blaybus_backend.domain.experience.dto.ExpStatusResponseDTO;
import blaybus.blaybus_backend.domain.experience.service.ExperienceService;
import blaybus.blaybus_backend.domain.member.service.MemberService;
import blaybus.blaybus_backend.global.common.SessionManager;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "경험치 조회 API")
@RestController
@RequestMapping("/experiences")
public class ExperienceController {

    private final ExperienceService experienceService;
    private final SessionManager sessionManager;

    @Autowired
    public ExperienceController(ExperienceService experienceService, SessionManager sessionManager) {
        this.experienceService = experienceService;
        this.sessionManager = sessionManager;
    }

    @Operation(description = "나의 경험치 목록 전체 조회")
    @GetMapping
    public String findAll(HttpSession session) {
        Long memberId = sessionManager.getMemberId(session);
        return null;
    }

    @Operation(description = "전체 경험치 상태 조회")
    @GetMapping("/status")
    public ResponseEntity<ExpStatusResponseDTO> getExpStatus(HttpSession session) {
        Long memberId = sessionManager.getMemberId(session);
        ExpStatusResponseDTO expStatus = experienceService.getExpStatusById(memberId);
        return ResponseEntity.ok(expStatus);
    }
}
