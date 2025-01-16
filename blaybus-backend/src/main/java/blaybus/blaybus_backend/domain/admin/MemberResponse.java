package blaybus.blaybus_backend.domain.admin;

import blaybus.blaybus_backend.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberResponse {
    private String name;
    private String employeeNumber;
    private String department;
    private String jobGroup;

    public static MemberResponse from(Member member) {
        return new MemberResponse(member.getName(), member.getEmployeeNumber(), member.getJobInfo().getDepartment(), member.getJobInfo().getJobGroup());
    }
}
