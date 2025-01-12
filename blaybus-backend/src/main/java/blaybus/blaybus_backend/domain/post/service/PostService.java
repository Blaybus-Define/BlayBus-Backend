package blaybus.blaybus_backend.domain.post.service;

import blaybus.blaybus_backend.domain.post.controller.SavePostRequest;
import blaybus.blaybus_backend.domain.post.dto.AllPostResponse;
import blaybus.blaybus_backend.domain.post.dto.SavePostResponse;
import blaybus.blaybus_backend.domain.post.entity.Post;
import blaybus.blaybus_backend.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        postRepository.findAll();
        return null;
    }
}
