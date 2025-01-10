package blaybus.blaybus_backend.domain.auth.dto;

import blaybus.blaybus_backend.domain.member.entity.JobRole;
import blaybus.blaybus_backend.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequest {

    private String employeeNumber;
    private String name;
    private LocalDate hireDate;
    private String department;
    private String jobGroup;
    private String jobRole;
    private String password;
    private int totalExperience;

    public Member toMember() {
        return Member.builder()
                .employeeNumber(this.employeeNumber)
                .name(this.name)
                .hireDate(this.hireDate)
                .department(this.department)
                .jobGroup(this.jobGroup)
                .jobRole(JobRole.create(this.jobRole))
                .password(this.password)
                .totalExperience(totalExperience)
                .build();
    }
}
