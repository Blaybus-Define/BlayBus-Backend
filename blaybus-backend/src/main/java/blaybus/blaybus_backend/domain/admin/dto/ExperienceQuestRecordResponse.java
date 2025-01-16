package blaybus.blaybus_backend.domain.admin.dto;

import blaybus.blaybus_backend.domain.experience.entity.GainExperience;
import blaybus.blaybus_backend.domain.quest.entity.AchievementLevel;
import blaybus.blaybus_backend.domain.quest.entity.MemberQuest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Builder
public class ExperienceQuestRecordResponse {

    private Long memberQuestId;

    // 유형 (리더부여/직부별/전사프로젝트/인사평가)
    private String type;

    // 항목 (ex. 월 특근)
    private String title;

    // 비고 (ex. 5회)
    private String description;

    // 주기 (연도, 월, 주차)
    private LocalDate date;

    // 경험치 (ex. 2000)
    private int experience;

    // 달성 정도 (퍼센트 혹은 값으로 표현)
    private String progress;

    // 부여 날짜
    private String assignedDate;

    // 완료 여부
    private boolean isCompleted;

    public static ExperienceQuestRecordResponse from(MemberQuest memberQuest) {
        AchievementLevel achievedLevel = memberQuest.getAchievedLevel();

        return ExperienceQuestRecordResponse.builder()
                .memberQuestId(memberQuest.getId())
                .type(String.valueOf(memberQuest.getQuest().getQuestType()))
                .title(memberQuest.getQuest().getTitle())
                .description(memberQuest.getQuest().getDescription())
                .experience(0)
                .date(memberQuest.getDate())
                .progress(String.valueOf(achievedLevel))
                .isCompleted(achievedLevel== AchievementLevel.FAIL)
                .build();
    }

    public static ExperienceQuestRecordResponse from(GainExperience experience) {

        return ExperienceQuestRecordResponse.builder()
                .type(experience.getType())
                .title(experience.getTitle())
                .experience(experience.getExp())
                .assignedDate(experience.getDescription())
                .progress(experience.getReason())
                .isCompleted(true)
                .build();
    }
}
