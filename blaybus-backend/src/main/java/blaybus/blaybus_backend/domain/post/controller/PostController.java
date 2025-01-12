package blaybus.blaybus_backend.domain.post.controller;

import blaybus.blaybus_backend.domain.post.dto.AllPostResponse;
import blaybus.blaybus_backend.domain.post.dto.SavePostResponse;
import blaybus.blaybus_backend.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    @GetMapping
    public ResponseEntity<AllPostResponse> findAllPost() {
        postService.findAllPost();
        return null;
    }

    @GetMapping
    public ResponseEntity<SavePostResponse> savePost(@RequestBody SavePostRequest savePostRequest) {
        return ResponseEntity.ok(postService.savePost(savePostRequest));
    }
}


