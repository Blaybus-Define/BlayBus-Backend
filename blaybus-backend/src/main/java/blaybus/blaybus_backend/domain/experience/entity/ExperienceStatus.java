package blaybus.blaybus_backend.domain.experience.entity;

import blaybus.blaybus_backend.domain.member.entity.Member;
import jakarta.persistence.*;

@Entity
public class ExperienceStatus {
    @Id
    @OneToOne
    @JoinColumn(name = "member_id", referencedColumnName = "id")
    private Member member;                 // 기본 키

    @Column(nullable = false)
    private String level;                 // 레벨

    @Column(nullable = false, columnDefinition = "integer default 0")
    private int firstHalfPerformanceExp;  // 상반기 인사평가 총 경험치

    @Column(nullable = false, columnDefinition = "integer default 0")
    private int secondHalfPerformanceExp; // 하반기 인사평가 총 경험치

    @Column(nullable = false, columnDefinition = "integer default 0")
    private int leaderExp;                // 리더부여 총 경험치

    @Column(nullable = false, columnDefinition = "integer default 0")
    private int jobRoleExp;               // 직무부여 총 경험치

    @Column(nullable = false, columnDefinition = "integer default 0")
    private int projectExp;               // 전사 프로젝트 총 경험치

    @Column(nullable = false, columnDefinition = "integer default 0")
    private int currentExp;               // 올해 총 경험치. 있어야할지? 그냥 위에 5개 반환해도 괜찮을것같기도.,.

    @Column(nullable = false, columnDefinition = "integer default 0")
    private int previousExp;              // 작년까지 얻은 총 경험치
}
