package blaybus.blaybus_backend.domain.member.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class MemberLevel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_category_id", nullable = false)
    private JobRole jobRole;

    @Column(nullable = false)
    private String levelName;

    @Column(nullable = false)
    private int minExperience;

    @Column(nullable = false)
    private int maxExperience;

    public MemberLevel(JobRole jobRole, String levelName) {
        this.jobRole = jobRole;
        this.levelName = levelName;
        this.minExperience = minExperience;
        this.maxExperience = maxExperience;
    }
}
