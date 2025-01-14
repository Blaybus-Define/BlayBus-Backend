package blaybus.blaybus_backend.domain.quest.controller;

import blaybus.blaybus_backend.domain.quest.dto.MemberQuestResponse;
import blaybus.blaybus_backend.domain.quest.service.MemberQuestService;
import blaybus.blaybus_backend.global.common.SessionManager;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "퀘스트 API")
@RequestMapping("/quests")
@RestController
@RequiredArgsConstructor
public class MemberQuestController {
    private final MemberQuestService memberQuestService;
    private final SessionManager sessionManager;

    @Operation(
            summary = "나의 퀘스트 조회",
            description = "week가 없으면 월 퀘스트 전체 조회, 있으면 주별 조회"
    )
    @Parameter(name = "Cookie", in = ParameterIn.HEADER, required = true)
    @GetMapping("/member")
    public ResponseEntity<MemberQuestResponse> getMyQuests(
            HttpSession session,
            @RequestParam Integer year,
            @RequestParam Integer month,
            @RequestParam(required = false) Integer week
    ) {
        Long memberId = sessionManager.getMemberId(session);
        return ResponseEntity.ok(memberQuestService.getMyQuests(memberId, year, month, week));
    }
}
