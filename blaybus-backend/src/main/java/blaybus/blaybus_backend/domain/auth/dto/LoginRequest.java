package blaybus.blaybus_backend.domain.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class LoginRequest {

    @Schema(description = "아이디", example = "minsukim", required = true)
    private String loginId;
    @Schema(description = "비밀번호", example = "1111", required = true)
    private String password;
    @Schema(description = "fcm 토큰", example = "12askjdb123nmn~~~")
    private String fcmToken;
}
