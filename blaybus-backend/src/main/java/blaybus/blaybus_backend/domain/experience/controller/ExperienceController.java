package blaybus.blaybus_backend.domain.experience.controller;

import blaybus.blaybus_backend.domain.experience.dto.ExpStatusResponseDTO;
import blaybus.blaybus_backend.domain.experience.dto.GainExpResponseDTO;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

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

    @Operation(description = "전체 경험치 현황 조회")
    @GetMapping("/status")
    public ResponseEntity<ExpStatusResponseDTO> getExpStatus(HttpSession session) {
        Long memberId = sessionManager.getMemberId(session);
        ExpStatusResponseDTO expStatus = experienceService.getExpStatusById(memberId);
        return ResponseEntity.ok(expStatus);
    }

    @Operation(description = "가장 최근 달성 경험치 조회")
    @GetMapping("/recent")
    public ResponseEntity<GainExpResponseDTO> getRecentExp(HttpSession session) {
        Long memberId = sessionManager.getMemberId(session);
        GainExpResponseDTO recentExp = experienceService.getRecentExpByMemberId(memberId);
        return ResponseEntity.ok(recentExp);
    }

    @Operation(description = "경험치 달성 목록 조회 (전체기간)")
    @GetMapping("/list/entire")
    public ResponseEntity<List<GainExpResponseDTO>> getEntireGainExp(HttpSession session, @RequestParam String type) {
        Long memberId = sessionManager.getMemberId(session);
        List<GainExpResponseDTO> gainExpResponseDTOs =
                experienceService.getEntireGainExpByMemberIdAndType(memberId, type);
        return ResponseEntity.ok(gainExpResponseDTOs);
    }

    @Operation(description = "경험치 달성 목록 조회 (올해)")
    @GetMapping("/list/annual")
    public ResponseEntity<List<GainExpResponseDTO>> getAnnualGainExp(HttpSession session, @RequestParam String type) {
        Long memberId = sessionManager.getMemberId(session);
        List<GainExpResponseDTO> gainExpResponseDTOs =
                experienceService.getAnnualGainExpByMemberIdAndType(memberId, type);
        return ResponseEntity.ok(gainExpResponseDTOs);
    }

    @Operation(description = "경험치 달성 목록 조회 (지정 기간)")
    @GetMapping("/list/period")
    public ResponseEntity<List<GainExpResponseDTO>> getPeriodGainExp(HttpSession session,
            @RequestParam String type, @RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        Long memberId = sessionManager.getMemberId(session);
        List<GainExpResponseDTO> gainExpResponseDTOs =
                experienceService.getPeriodGainExpByMemberIdAndType(memberId, type, startDate, endDate);
        return ResponseEntity.ok(gainExpResponseDTOs);
    }
}
