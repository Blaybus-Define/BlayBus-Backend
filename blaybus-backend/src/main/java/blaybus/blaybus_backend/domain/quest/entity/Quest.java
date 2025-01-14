package blaybus.blaybus_backend.domain.quest.entity;

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

    private int maxCriterionExperience; // MAX 기준을 만족하는 경험치

    private int mediumCriterionExperience; // MID 기준을 만족하는 경험치
    private LocalDate startDate; // 시작 날짜
    private LocalDate endDate; // 종료 날짜
    private String department; // TODO : 소속 (객체로 빼내기)
    private String jobGroup; // TODO : 직무 그룹 (객체로 빼내기)

    @Builder
    public Quest(String title, String description, QuestType questType, QuestFrequency frequency, int maxCriterionExperience, int mediumCriterionExperience, LocalDate startDate, LocalDate endDate, String department, String jobGroup) {
        this.title = title;
        this.description = description;
        this.questType = questType;
        this.frequency = frequency;
        this.maxCriterionExperience = maxCriterionExperience;
        this.mediumCriterionExperience = mediumCriterionExperience;
        this.startDate = startDate;
        this.endDate = endDate;
        this.department = department;
        this.jobGroup = jobGroup;
    }
}
