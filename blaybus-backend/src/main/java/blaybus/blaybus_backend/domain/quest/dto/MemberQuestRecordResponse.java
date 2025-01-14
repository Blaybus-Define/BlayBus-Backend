package blaybus.blaybus_backend.domain.quest.dto;

import blaybus.blaybus_backend.domain.quest.entity.MemberQuest;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class MemberQuestRecordResponse {
    private String title;
    private String type;
    private String achievedLevel;
    private LocalDate achievementDate;
    private String description;
    private String date;


    public static MemberQuestRecordResponse from(MemberQuest quest) {
        return new MemberQuestRecordResponse(quest);
    }

    public MemberQuestRecordResponse(MemberQuest quest) {
        this.title = quest.getTitle();
        this.type = quest.getType().getDescription();
        this.achievedLevel = quest.getAchievedLevel().getDescription();
        this.achievementDate = quest.getAchievementDate();
        this.description = quest.getDescription();
        this.date = String.valueOf(quest.getDate());
    }
}
