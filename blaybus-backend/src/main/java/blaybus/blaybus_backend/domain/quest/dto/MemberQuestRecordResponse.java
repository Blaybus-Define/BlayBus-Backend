package blaybus.blaybus_backend.domain.quest.dto;

import blaybus.blaybus_backend.domain.quest.entity.MemberQuest;
import lombok.Getter;


@Getter
public class MemberQuestRecordResponse {
    private String title;
    private String questType;
    private String achievedLevel;
    private String description;
    private String date;
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
