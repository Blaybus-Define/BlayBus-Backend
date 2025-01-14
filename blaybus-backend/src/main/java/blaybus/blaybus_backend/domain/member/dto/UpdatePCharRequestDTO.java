package blaybus.blaybus_backend.domain.member.dto;

import blaybus.blaybus_backend.domain.member.entity.ProfileCharacter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePCharRequestDTO {
    @Schema(description = "변경할 캐릭터", example = "남3", required = true)
    private ProfileCharacter profileCharacter;
}
