package blaybus.blaybus_backend.domain.admin.controller;

import blaybus.blaybus_backend.domain.admin.dto.ApproveQuestRequest;
import blaybus.blaybus_backend.domain.admin.dto.ExperienceQuestRequest;
import blaybus.blaybus_backend.domain.admin.dto.ExperienceQuestResponse;
import blaybus.blaybus_backend.domain.admin.dto.MemberResponse;
import blaybus.blaybus_backend.domain.experience.service.ExperienceService;
import blaybus.blaybus_backend.domain.member.service.MemberService;
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
    private final MemberService memberService;


    //퀘스트 완료 처리
    @PutMapping("/quest/approve")
    public ResponseEntity<Void> approveQuest(@RequestBody ApproveQuestRequest approveQuestRequest) {
        questService.approveQuest(approveQuestRequest);
        return ResponseEntity.ok().build();
    }

    //경험치 생성 (인사평가, 전사 프로젝트)
    @PostMapping("/quest/experience")
    public ResponseEntity<Void> grantExperience(@RequestBody ExperienceQuestRequest experienceQuestRequest) {
        experienceService.grantExperience(experienceQuestRequest);
        return ResponseEntity.ok().build();
    }

    //퀘스트 조회 (미완료, 실패 퀘스트만)
    @GetMapping("/quest/member")
    public ResponseEntity<ExperienceQuestResponse> findMemberQuest(@RequestParam String loginId) {
        return ResponseEntity.ok(experienceService.findNotAchievedOrFailedQuests(loginId));
    }

    //경험치 조회 (완료 퀘스트 포함)
    @GetMapping("/quest/experience")
    public ResponseEntity<ExperienceQuestResponse> findMemberExperience(@RequestParam String loginId) {
        return ResponseEntity.ok(experienceService.findMyExperiences(loginId));

    }

    //퀘스트 수동 생성
    @PostMapping("/quest")
    public ResponseEntity<Void> createQuest(@RequestBody ExperienceQuestRequest experienceQuestRequest) {
        questService.createQuest(experienceQuestRequest);
        return ResponseEntity.ok().build();
    }

    //멤버 조회
    @GetMapping("/member")
    public ResponseEntity<MemberResponse> findMember(@RequestParam String loginId) {
        return ResponseEntity.ok(memberService.findMemberByLoginId(loginId));
    }
}
