package blaybus.blaybus_backend.domain.post.controller;

import blaybus.blaybus_backend.domain.member.entity.JobRole;
import blaybus.blaybus_backend.domain.member.entity.Member;
import blaybus.blaybus_backend.domain.post.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SavePostRequest {

    private String title;

    private String content;

    public Post toPost() {
        return Post.builder()
                .title(this.title)
                .content(this.content)
                .build();
    }
}
