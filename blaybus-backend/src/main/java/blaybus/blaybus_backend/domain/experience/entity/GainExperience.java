package blaybus.blaybus_backend.domain.experience.entity;

import blaybus.blaybus_backend.domain.member.entity.Member;
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
public class GainExperience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id", referencedColumnName = "id")
    private Member member;

    @Column(nullable = false)
    private String title;           // 제목

    @Column(nullable = false)
    private String type;            // 구분

    @Column(nullable = false)
    private LocalDate date;         // 경험치 수령 날짜

    @Column
    private String reason;          // 달성 여부

    @Column(nullable = false)
    private int exp;             // 획득 경험치

    @Column
    private String description;     // 비고

}
