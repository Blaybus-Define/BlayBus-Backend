package blaybus.blaybus_backend.domain.admin;

import blaybus.blaybus_backend.domain.quest.entity.Quest;
import blaybus.blaybus_backend.domain.quest.entity.QuestType;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ExperienceQuestRequest {

    private String loginId;

    /**
     * 유형
     * LEADER_ASSIGNMENT : 리더부여 퀘스트
     * TASK : 직무별 퀘스트
     * PERFORMANCE_EVALUATION : 인사 평가
     * CORPORATE PROJECT : 전사 프로젝트
     */
    private String type;

    // 항목(ex. 월 특근)
    private String title;

    // 비고(ex. 5회)
    private String description;

    private LocalDate date;

    // 경험치(ex.2000)
    private int experience;

    private int maxCriterionExperience;
    private int mediumCriterionExperience;

    public Quest toQuest() {
        return Quest.builder()
                .title(this.title)
                .description(this.description)
                .questType(QuestType.valueOf(this.type))
                .maxCriterionExperience(this.maxCriterionExperience)
                .mediumCriterionExperience(this.mediumCriterionExperience)
                .build();
    }
}
