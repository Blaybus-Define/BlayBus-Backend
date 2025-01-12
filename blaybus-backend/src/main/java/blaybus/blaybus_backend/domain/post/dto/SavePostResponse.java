package blaybus.blaybus_backend.domain.post.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class SavePostResponse {
    @Schema(description = "글 등록 성공 메세지", required = true)
    private String message = "글 동록이 완료되었습니다.";
}
