package blaybus.blaybus_backend.domain.auth.dto;

import lombok.Getter;

@Getter
public class LoginRequest {

    private String employeeNumber;
    private String password;
}
