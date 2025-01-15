package blaybus.blaybus_backend.domain.admin;

import blaybus.blaybus_backend.domain.experience.service.ExperienceService;
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
    private final ExperienceService experienceService;

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

    @PutMapping("/quest/approve")
    public ResponseEntity<Void> approveQuest(@RequestBody ApproveQuestRequest approveQuestRequest) {
        questService.approveQuest(approveQuestRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/quest/member")
    public ResponseEntity<MemberQuestResponse> findMemberQuest(@RequestParam String loginId) {
        return ResponseEntity.ok(questService.getMemberQuests(loginId));
    }

    //인사평가, 전사 프로젝트 경험치 적립 (근데 따로 만들어야 할지 고민..)
    @PostMapping("/quest/experience")
    public ResponseEntity<Void> grantExperience(@RequestBody ExperienceGrantRequest experienceGrantRequest) {
        experienceService.grantExperience(experienceGrantRequest);
        return ResponseEntity.ok().build();
    }
}
