package blaybus.blaybus_backend.domain.quest.entity;

import blaybus.blaybus_backend.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Entity
@Getter
public class MemberQuest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    @Enumerated(EnumType.STRING)
    private QuestType questType;

    @Enumerated(EnumType.STRING)
    private QuestFrequency frequency;

    @Enumerated(EnumType.STRING)
    private AchievementLevel achievedLevel;

    private LocalDate achievementDate;

    private String description;

    private LocalDate date;
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
}
