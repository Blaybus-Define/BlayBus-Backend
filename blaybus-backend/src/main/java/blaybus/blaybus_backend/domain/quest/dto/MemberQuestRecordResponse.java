package blaybus.blaybus_backend.domain.quest.dto;

import blaybus.blaybus_backend.domain.quest.entity.MemberQuest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;


@Getter
public class MemberQuestRecordResponse {
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


    public static MemberQuestRecordResponse from(MemberQuest quest) {
        return new MemberQuestRecordResponse(quest);
    }

    public MemberQuestRecordResponse(MemberQuest quest) {
        this.title = quest.getTitle();
        this.questType = quest.getQuestType().getDescription();
        this.achievedLevel = String.valueOf(quest.getAchievedLevel());
        this.description = quest.getDescription();
        this.date = String.valueOf(quest.getDate());
        this.questFrequency = String.valueOf(quest.getFrequency());
    }
}
