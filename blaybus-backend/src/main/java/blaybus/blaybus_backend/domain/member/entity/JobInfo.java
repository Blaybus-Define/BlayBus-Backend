package blaybus.blaybus_backend.domain.member.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JobInfo {

    private String department; // 소속
    private String jobGroup;   // 직무 그룹
    private String jobRole;    // 직군

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobInfo jobInfo = (JobInfo) o;
        return Objects.equals(department, jobInfo.department) &&
                Objects.equals(jobGroup, jobInfo.jobGroup) &&
                Objects.equals(jobRole, jobInfo.jobRole);
    }

    @Override
    public int hashCode() {
        return Objects.hash(department, jobGroup, jobRole);
    }
}
