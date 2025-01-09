package blaybus.blaybus_backend.domain.member.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
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

    @Column(nullable = false)
    private String jobGroup; // 직무그룹

    @Enumerated(EnumType.STRING)
    private MemberLevel memberLevel; // 레벨

    @Column(nullable = false)
    private String password; // 패스워드

    @Column(nullable = false, columnDefinition = "integer default 0")
    private int totalExperience = 0; // 총 경험치
}
