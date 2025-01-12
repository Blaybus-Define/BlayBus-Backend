package blaybus.blaybus_backend.domain.post.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class AllPostResponse {

    @Schema(description = "게시글 응답 리스트", required = true)
    private List<PostResponse> postResponseList;

    public static AllPostResponse of(List<PostResponse> postResponseList) {
        return new AllPostResponse(postResponseList);
    }
}
