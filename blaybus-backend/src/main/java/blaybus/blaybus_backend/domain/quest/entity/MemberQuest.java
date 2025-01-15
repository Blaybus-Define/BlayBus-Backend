package blaybus.blaybus_backend.domain.quest.entity;

import blaybus.blaybus_backend.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
public class MemberQuest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private AchievementLevel achievedLevel;

    private LocalDate achievementDate;

    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "quest_id")
    private Quest quest;

    @Builder
    public MemberQuest(Member member, Quest quest, LocalDate date) {
        this.member = member;
        this.quest = quest;
        this.date = date;
        this.achievedLevel = AchievementLevel.NOT_ACHIEVED;
    }

    public void updateAchievedLevel(AchievementLevel achievedLevel) {
        this.achievedLevel = achievedLevel;
    }
}
