package blaybus.blaybus_backend.domain.experience.entity;

import blaybus.blaybus_backend.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class ExperienceStatus {

    @Id
    @Column(name = "member_id")
    private Long memberId;       // 기본 키

    @OneToOne
    @MapsId
    @JoinColumn(name = "member_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Member member;        // 외래 키로 Member 엔티티와 연결

    @Column(nullable = false, columnDefinition = "integer default 0")
    private int firstHalfPerformanceExp = 0;  // 상반기 인사평가 총 경험치

    @Column(nullable = false, columnDefinition = "integer default 0")
    private int secondHalfPerformanceExp = 0; // 하반기 인사평가 총 경험치

    @Column(nullable = false, columnDefinition = "integer default 0")
    private int leaderExp = 0;                // 리더부여 총 경험치

    @Column(nullable = false, columnDefinition = "integer default 0")
    private int jobRoleExp = 0;               // 직무별 총 경험치

    @Column(nullable = false, columnDefinition = "integer default 0")
    private int projectExp = 0;               // 올해 전사 프로젝트 총 경험치

    @Column(nullable = false, columnDefinition = "integer default 0")
    private int previousExp = 0;              // 작년까지 얻은 총 경험치
}
