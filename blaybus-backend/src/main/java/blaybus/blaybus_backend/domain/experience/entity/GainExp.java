package blaybus.blaybus_backend.domain.experience.entity;

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
public class GainExp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;           // 제목

    @Column(nullable = false)
    private String type;            // 구분

    @Column(nullable = false)
    private LocalDate date;         // 달성 날짜

    @Column
    private String reason;          // 달성 여부

    @Column(nullable = false)
    private String exp;             // 획득 경험치

    @Column
    private String period;          // 획득 주기

    @Column
    private String description;     // 비고

}
