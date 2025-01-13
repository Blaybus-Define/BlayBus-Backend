package blaybus.blaybus_backend.domain.member.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 기본 키 (자동 생성)

    @Column(nullable = false, unique = true)
    private String employeeNumber; // 사번

    @Column(nullable = false)
    private String name; // 이름

    @Column(nullable = false)
    private LocalDate hireDate; // 입사일

    @Column(nullable = false)
    private String department; // 소속

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "job_group_id", nullable = false)
    private JobRole jobRole; // 직군

    private String jobGroup; // 직무 그룹

    @Column(nullable = false)
    private String loginId; // 로그인 아이디

    @Column(nullable = false)
    private String password; // 패스워드

    @Column(nullable = false, columnDefinition = "integer default 0")
    private int totalExperience = 0; // 총 경험치

    @Column(length = 500)
    private String fcmToken;
}
