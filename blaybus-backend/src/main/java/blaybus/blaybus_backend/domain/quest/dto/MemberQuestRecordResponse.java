package blaybus.blaybus_backend.domain.quest.dto;

import blaybus.blaybus_backend.domain.quest.entity.AchievementLevel;
import blaybus.blaybus_backend.domain.quest.entity.MemberQuest;
import blaybus.blaybus_backend.domain.quest.entity.Quest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;


@Getter
public class MemberQuestRecordResponse {
    @Schema(description = "퀘스트 id", example = "1")
    private Long id;
    @Schema(description = "퀘스트명", example = "월 특근")
    private String title;
    @Schema(description = "퀘스트타입(직무/리더부여)", example = "직무")
    private String questType;
    @Schema(description = "달성정도(NOT_ACHIEVED/MEDIUM/MAX)", example = "MAX")
    private String achievedLevel;
    @Schema(description = "퀘스트 설명", example = "5회")
    private String description;
    @Schema(description = "퀘스트 부여 날짜", example = "2025-01-01")
    private String date;
    @Schema(description = "퀘스트 주기(MONTHLY/WEEKLY)", example = "MONTHLY")
    private String questFrequency;

    @Schema(description = "부여 경험치", example = "50")
    private Integer experience;


    public static MemberQuestRecordResponse from(MemberQuest quest) {
        return new MemberQuestRecordResponse(quest);
    }

    public MemberQuestRecordResponse(MemberQuest memberQuest) {
        Quest quest = memberQuest.getQuest();
        AchievementLevel achievedLevel = memberQuest.getAchievedLevel();

        this.id = memberQuest.getId();
        this.title = quest.getTitle();
        this.questType = quest.getQuestType().getDescription();
        this.achievedLevel = String.valueOf(achievedLevel);
        this.description = quest.getDescription();
        this.date = String.valueOf(memberQuest.getDate());
        this.questFrequency = String.valueOf(quest.getFrequency());
        this.experience = switch (achievedLevel) {
            case MAX -> quest.getMaxCriterionExperience();
            case FAIL -> 0;
            case MEDIUM -> quest.getMediumCriterionExperience();
            default -> null;
        };

    }
}
