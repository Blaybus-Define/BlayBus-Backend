package blaybus.blaybus_backend.domain.quest.controller;

import blaybus.blaybus_backend.domain.quest.dto.MemberQuestResponse;
import blaybus.blaybus_backend.domain.quest.service.MemberQuestService;
import blaybus.blaybus_backend.global.common.SessionManager;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/quests")
@RestController
@RequiredArgsConstructor
public class MemberQuestController {
    private final MemberQuestService memberQuestService;
    private final SessionManager sessionManager;

    //퀘스트 조회
    @GetMapping
    public ResponseEntity<MemberQuestResponse> getSortedQuests(
            HttpSession session,
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) Integer month
    ) {
        Long memberId = sessionManager.getMemberId(session);
        return ResponseEntity.ok(memberQuestService.getMyQuests(memberId, year, month));
    }
}
