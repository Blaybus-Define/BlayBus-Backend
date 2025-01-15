package blaybus.blaybus_backend.domain.admin;

import blaybus.blaybus_backend.domain.quest.controller.QuestSaveRequest;
import blaybus.blaybus_backend.domain.quest.dto.MemberQuestResponse;
import blaybus.blaybus_backend.domain.quest.service.QuestService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "어드민 API")
@RequestMapping("/admin")
@RestController
@RequiredArgsConstructor
public class AdminController {

    private final QuestService questService;

    @PostMapping("/quest/job")
    public ResponseEntity<Void> createJobQuest(@RequestBody QuestSaveRequest questSaveRequest) {
        questService.createJobQuest(questSaveRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/quest/leader")
    public ResponseEntity<Void> createLeaderQuest(@RequestBody QuestSaveRequest questSaveRequest) {
        questService.createLeaderQuest(questSaveRequest);
        return ResponseEntity.ok().build();
    }

    //직무별 퀘스트 달성 처리 -> 상태 바꾸고 경험치 적립
    @PutMapping("/quest/job/approve")
    public ResponseEntity<MemberQuestResponse> approveJobQuest(@RequestBody ApproveJobQuestRequest approveJobQuestRequest) {
        return null;
    }

    //리더부여 퀘스트 달성 처리
    @PutMapping("/quest/leader/approve")
    public ResponseEntity<MemberQuestResponse> approveLeaderQuest(@RequestBody ApproveLeaderQuestRequest approveLeaderQuestRequest) {
        return null;
    }

    @GetMapping("/quest/member")
    public ResponseEntity<MemberQuestResponse> findMemberQuest(@RequestParam String loginId) {
        return ResponseEntity.ok(questService.getMemberQuests(loginId));
    }
}
