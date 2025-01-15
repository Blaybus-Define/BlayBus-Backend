package blaybus.blaybus_backend.domain.experience.entity;

import blaybus.blaybus_backend.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class ExperienceStatus {
    @Id
    @OneToOne
    @JoinColumn(name = "member_id", referencedColumnName = "id")
    @PrimaryKeyJoinColumn
    private Member member;                 // 기본 키

    @Column(nullable = false, columnDefinition = "integer default 0")
    private int firstHalfPerformanceExp;  // 상반기 인사평가 총 경험치

    @Column(nullable = false, columnDefinition = "integer default 0")
    private int secondHalfPerformanceExp; // 하반기 인사평가 총 경험치

    @Column(nullable = false, columnDefinition = "integer default 0")
    private int leaderExp;                // 리더부여 총 경험치

    @Column(nullable = false, columnDefinition = "integer default 0")
    private int jobRoleExp;               // 직무별 총 경험치

    @Column(nullable = false, columnDefinition = "integer default 0")
    private int projectExp;               // 올해 전사 프로젝트 총 경험치

    @Column(nullable = false, columnDefinition = "integer default 0")
    private int previousExp;              // 작년까지 얻은 총 경험치
}
