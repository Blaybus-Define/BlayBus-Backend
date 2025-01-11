package blaybus.blaybus_backend.domain.experience.controller;

import blaybus.blaybus_backend.domain.experience.service.ExperienceService;
import blaybus.blaybus_backend.domain.member.entity.Member;
import blaybus.blaybus_backend.global.common.SessionManager;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/experiences")
public class ExperienceController {

    private final ExperienceService experienceService;
    private final SessionManager sessionManager;

    @Operation(description = "나의 경험치 목록 전체 조회")
    @GetMapping
    public String findAll(HttpSession session) {
        Long memberId = sessionManager.getMemberId(session);
        experienceService.findMyAllExperiences(memberId);
        return null;
    }

}
