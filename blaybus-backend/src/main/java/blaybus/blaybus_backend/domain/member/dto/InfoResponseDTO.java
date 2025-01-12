package blaybus.blaybus_backend.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
public class InfoResponseDTO {
    private String employeeNumber;
    private String name;
    private LocalDate hireDate;
    private String department;
    private String jobGroup;
    private String loginId;
    private int totalExperience;
    private String levelName;
    private String jobGroup;
    private String loginId;
}
