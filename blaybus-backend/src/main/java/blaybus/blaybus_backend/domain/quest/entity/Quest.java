package blaybus.blaybus_backend.domain.quest.entity;

import blaybus.blaybus_backend.domain.member.entity.JobInfo;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
public class Quest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private QuestType questType;

    @Enumerated(EnumType.STRING)
    private QuestFrequency frequency;

    private int maxCriterionExperience; // MAX일 때 부여하는 경험치

    private int mediumCriterionExperience; // MID일 때 부여하는 경험치

    @Embedded
    private JobInfo jobInfo;

    @Builder
    public Quest(String title, String description, QuestType questType, QuestFrequency frequency, int maxCriterionExperience, int mediumCriterionExperience, LocalDate startDate, LocalDate endDate, String department, String jobGroup, String jobRole) {
        this.title = title;
        this.description = description;
        this.questType = questType;
        this.frequency = frequency;
        this.maxCriterionExperience = maxCriterionExperience;
        this.mediumCriterionExperience = mediumCriterionExperience;
        this.jobInfo = new JobInfo(department, jobGroup, jobRole);
    }

}
