package blaybus.blaybus_backend.domain.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class LoginRequest {

    @Schema(description = "사원 번호", example = "2023010101", required = true)
    private String employeeNumber;
    @Schema(description = "비밀번호", example = "1111", required = true)
    private String password;
}
