package blaybus.blaybus_backend.domain.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePwRequestDTO {
    @Schema(description = "기존 패스워드", example = "password", required = true)
    private String oldPassword;
    @Schema(description = "새로운 패스워드", example = "password123", required = true)
    private String newPassword;
}
