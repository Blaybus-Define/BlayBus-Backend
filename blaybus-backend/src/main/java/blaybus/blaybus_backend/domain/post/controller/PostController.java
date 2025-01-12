package blaybus.blaybus_backend.domain.post.controller;

import blaybus.blaybus_backend.domain.post.dto.AllPostResponse;
import blaybus.blaybus_backend.domain.post.dto.SavePostRequest;
import blaybus.blaybus_backend.domain.post.dto.SavePostResponse;
import blaybus.blaybus_backend.domain.post.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    @Operation(summary = "모든 게시글 조회", description = "Header의 세션 ID로 사용자 인증")
    @GetMapping
    public ResponseEntity<AllPostResponse> findAllPost() {
        return ResponseEntity.ok(postService.findAllPost());
    }

    @Operation(summary = "게시글 저장", description = "Header의 세션 ID로 사용자 인증")
    @PostMapping
    public ResponseEntity<SavePostResponse> savePost(@RequestBody SavePostRequest savePostRequest) {
        return ResponseEntity.ok(postService.savePost(savePostRequest));
    }
}


