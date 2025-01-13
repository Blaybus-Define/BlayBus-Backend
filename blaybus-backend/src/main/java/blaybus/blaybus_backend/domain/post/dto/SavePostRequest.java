package blaybus.blaybus_backend.domain.post.dto;

import blaybus.blaybus_backend.domain.member.entity.JobRole;
import blaybus.blaybus_backend.domain.member.entity.Member;
import blaybus.blaybus_backend.domain.post.entity.Post;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

@Getter
@AllArgsConstructor
public class SavePostRequest {

    @Schema(description = "제목", example = "minsukim", required = true)
    private String title;

    @Schema(description = "내용", example = "잡초이스 공고", required = true)
    @Size(max = 1000)
    private String content;

    public Post toPost() {
        return Post.builder()
                .title(this.title)
                .content(this.content)
                .build();
    }
}
