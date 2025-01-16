package blaybus.blaybus_backend.domain.experience.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ExpStatusResponseDTO {
    @Schema(description = "상반기 인사평가 총 경험치 ", example = "1000")
    private int firstHalfPerformanceExp;
    @Schema(description = "하반기 인사평가 총 경험치 ", example = "1500")
    private int secondHalfPerformanceExp;
    @Schema(description = "리더부여 퀘스트 총 경험치 ", example = "2300")
    private int leaderExp;
    @Schema(description = "직무별 퀘스트 총 경험치 ", example = "1700")
    private int jobRoleExp;
    @Schema(description = "올해 획득한 경험치 ", example = "6500")
    private int annualExp;
    @Schema(description = "올해 전사 프로젝트 총 경험치 ", example = "600")
    private int projectExp;
    @Schema(description = "작년까지 획득한 경험치 ", example = "5000")
    private int previousExp;
}
