package blaybus.blaybus_backend.domain.quest.controller;

import blaybus.blaybus_backend.domain.quest.entity.Quest;
import blaybus.blaybus_backend.domain.quest.entity.QuestFrequency;
import blaybus.blaybus_backend.domain.quest.entity.QuestType;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class QuestSaveRequest {
    private String title;
    private String description;
    private QuestFrequency frequency;
    private int maxCriterionExperience;
    private int mediumCriterionExperience;
    private LocalDate startDate;
    private LocalDate endDate;
    private String department; //소속
    private String jobGroup; //직무그룹

    public Quest toQuest(QuestType questType) {
        return Quest.builder()
                .title(this.title)
                .description(this.description)
                .questType(questType)
                .frequency(this.frequency)
                .maxCriterionExperience(this.maxCriterionExperience)
                .mediumCriterionExperience(this.mediumCriterionExperience)
                .startDate(this.startDate)
                .endDate(this.endDate)
                .department(this.department)
                .jobGroup(this.jobGroup)
                .build();
    }
}
