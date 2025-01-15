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

    @Embedded
    private JobInfo jobInfo;

    @Column(nullable = false)
    private String loginId; // 로그인 아이디

    @Column(nullable = false)
    private String password; // 패스워드

    @Column(nullable = false, columnDefinition = "integer default 0")
    private int totalExperience = 0; // 총 경험치

    @Column(nullable = false,
            columnDefinition = "ENUM('DEFAULT','FLOWER','MUSIC','CLOUD','HEART','STAR') DEFAULT 'DEFAULT'")
    @Enumerated(EnumType.STRING)
    private ProfileCharacter profileCharacter; // 프로필 캐릭터

    @Column(length = 500)
    private String fcmToken;

    private String level;

    public void plusExperience(int experience) {
        this.totalExperience += experience;
    }
}
