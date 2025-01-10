package blaybus.blaybus_backend.domain.member.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class JobRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private JobRoleType jobRoleType;

    public static JobRole create(String jobRoleType) {
        JobRole jobRole = new JobRole();
        jobRole.jobRoleType = JobRoleType.fromString(jobRoleType);
        return jobRole;
    }
}
