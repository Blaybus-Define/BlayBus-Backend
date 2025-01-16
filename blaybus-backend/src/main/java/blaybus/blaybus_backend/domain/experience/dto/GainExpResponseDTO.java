package blaybus.blaybus_backend.domain.experience.dto;

import blaybus.blaybus_backend.domain.experience.entity.GainExperience;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GainExpResponseDTO {
    @Schema(description = "제목", example = "월특근")
    private String title;
    @Schema(description = "구분", example = "리더부여")
    private String type;
    @Schema(description = "경험치 수령 날짜", example = "2024-05-12")
    private LocalDate date;
    @Schema(description = "달성 여부", example = "MAX달성")
    private String reason;
    @Schema(description = "획득 경험치", example = "100")
    private Integer exp;
    @Schema(description = "비고", example = "1월")
    private String description;

    public static GainExpResponseDTO fromEntity(GainExperience gainExperience) {
        return GainExpResponseDTO.builder()
                .title(gainExperience.getTitle())
                .type(gainExperience.getType())
                .date(gainExperience.getDate())
                .reason(gainExperience.getReason())
                .exp(gainExperience.getExp())
                .description(gainExperience.getDescription())
                .build();
    }
}
