package blaybus.blaybus_backend.domain.experience;

import blaybus.blaybus_backend.domain.member.entity.Member;
import jakarta.persistence.*;

import java.time.LocalDate;

public class ExperienceRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member; // 경험치를 얻은 회원

    @Column(nullable = false)
    private int points; // 획득한 경험치

    @Column(nullable = false)
    private LocalDate date; // 획득 날짜

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ExperienceType experienceType; // 경험치 종류
}
