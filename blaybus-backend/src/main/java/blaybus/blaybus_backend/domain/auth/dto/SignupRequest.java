package blaybus.blaybus_backend.domain.auth.dto;

import blaybus.blaybus_backend.domain.member.entity.JobInfo;
import blaybus.blaybus_backend.domain.member.entity.Member;
import blaybus.blaybus_backend.domain.member.entity.ProfileCharacter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequest {

    @Schema(description = "사원 번호", example = "123456", required = true)
    private String employeeNumber;

    @Schema(description = "이름", example = "홍길동", required = true)
    private String name;

    @Schema(description = "입사일", example = "2023-01-01", required = true, format = "date")
    private LocalDate hireDate;

    @Schema(description = "소속", example = "사업기획팀", required = true)
    private String department;

    @Schema(description = "직무 그룹", example = "1", required = true)
    private String jobGroup;

    @Schema(description = "직군", example = "BRANDING", required = true)
    private String jobRole;

    @Schema(description = "아이디", example = "minsu", required = true)
    private String loginId;

    @Schema(description = "비밀번호", example = "1111", required = true)
    private String password;



    public Member toMember() {
        return Member.builder()
                .employeeNumber(this.employeeNumber)
                .name(this.name)
                .hireDate(this.hireDate)
                .jobInfo(new JobInfo(this.department, this.jobGroup, this.jobRole))
                .loginId(this.loginId)
                .password(this.password)
                .profileCharacter(ProfileCharacter.DEFAULT)
                .build();
    }
}
