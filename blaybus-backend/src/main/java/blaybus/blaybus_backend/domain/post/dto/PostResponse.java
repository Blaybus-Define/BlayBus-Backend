package blaybus.blaybus_backend.domain.post.dto;

import blaybus.blaybus_backend.domain.post.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PostResponse {

    private String title;
    private String content;

    public static PostResponse of(Post post) {
        return new PostResponse(post.getTitle(), post.getContent());
    }
}
