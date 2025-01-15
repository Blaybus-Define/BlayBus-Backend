package blaybus.blaybus_backend.domain.experience.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ExpStatusResponseDTO {
    private int firstHalfPerformanceExp;
    private int secondHalfPerformanceExp;
    private int leaderExp;
    private int jobRoleExp;
    private int annualExp;
    private int projectExp;
    private int previousExp;
}
