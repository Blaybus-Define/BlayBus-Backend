package blaybus.blaybus_backend.domain.member.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePwRequestDTO {
    private String oldPassword;
    private String newPassword;
}
