package blaybus.blaybus_backend.domain.post.controller;

import blaybus.blaybus_backend.domain.post.dto.AllPostResponse;
import blaybus.blaybus_backend.domain.post.dto.SavePostRequest;
import blaybus.blaybus_backend.domain.post.dto.SavePostResponse;
import blaybus.blaybus_backend.domain.post.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "게시글 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    @Operation(summary = "모든 게시글 조회")
    @Parameter(name = "Cookie", in = ParameterIn.HEADER, required = true)
    @GetMapping
    public ResponseEntity<AllPostResponse> findAllPost() {
        return ResponseEntity.ok(postService.findAllPost());
    }

    @Operation(summary = "게시글 저장")
    @Parameter(name = "Cookie", in = ParameterIn.HEADER, required = true)
    @PostMapping
    public ResponseEntity<SavePostResponse> savePost(@RequestBody SavePostRequest savePostRequest) {
        return ResponseEntity.ok(postService.savePost(savePostRequest));
    }
}


