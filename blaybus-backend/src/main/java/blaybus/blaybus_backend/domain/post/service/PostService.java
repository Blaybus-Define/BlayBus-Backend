package blaybus.blaybus_backend.domain.post.service;

import blaybus.blaybus_backend.domain.post.dto.SavePostRequest;
import blaybus.blaybus_backend.domain.post.dto.AllPostResponse;
import blaybus.blaybus_backend.domain.post.dto.PostResponse;
import blaybus.blaybus_backend.domain.post.dto.SavePostResponse;
import blaybus.blaybus_backend.domain.post.entity.Post;
import blaybus.blaybus_backend.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    public SavePostResponse savePost(SavePostRequest savePostRequest) {
        Post post = savePostRequest.toPost();
        postRepository.save(post);
        return new SavePostResponse();
    }

    public AllPostResponse findAllPost() {
        List<PostResponse> postResponseList = postRepository.findAll().stream()
                .map(post -> PostResponse.of(post))
                .toList();
        return AllPostResponse.of(postResponseList);
    }
}
