package blaybus.blaybus_backend.domain.quest.controller;

import blaybus.blaybus_backend.domain.quest.entity.Quest;
import blaybus.blaybus_backend.domain.quest.entity.QuestFrequency;
import blaybus.blaybus_backend.domain.quest.entity.QuestType;
import lombok.Getter;

import java.util.List;

@Getter
public class QuestSaveRequest {
    private String title;
    private String description;
    private QuestFrequency frequency;
    private int maxCriterionExperience;
    private int mediumCriterionExperience;
    private String department; //소속
    private String jobGroup; //직무그룹
    private String jobRole; //직군
    private List<String> loginIds;

    public Quest toQuest(QuestType questType) {
        return Quest.builder()
                .title(this.title)
                .description(this.description)
                .questType(questType)
                .frequency(this.frequency)
                .maxCriterionExperience(this.maxCriterionExperience)
                .mediumCriterionExperience(this.mediumCriterionExperience)
                .department(this.department)
                .jobGroup(this.jobGroup)
                .jobRole(this.jobRole)
                .build();
    }
}
