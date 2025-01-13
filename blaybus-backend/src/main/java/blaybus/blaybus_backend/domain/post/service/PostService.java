package blaybus.blaybus_backend.domain.post.service;

import blaybus.blaybus_backend.domain.member.repository.MemberRepository;
import blaybus.blaybus_backend.domain.notification.dto.FcmNotificationDTO;
import blaybus.blaybus_backend.domain.notification.entity.NotificationType;
import blaybus.blaybus_backend.domain.notification.service.NotificationService;
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
    private final MemberRepository memberRepository;
    private final NotificationService notificationService;
    private static final String POST_NOTIFICATION_TITLE = "새로운 게시글이 등록되었습니다";


    public SavePostResponse savePost(SavePostRequest savePostRequest) {
        Post post = savePostRequest.toPost();
        postRepository.save(post);
        //등록 되면 회원에게 알림
        memberRepository.findAll().stream()
                .filter(member -> member.getFcmToken() != null)
                .forEach(member -> notificationService.sendNotification(
                        new FcmNotificationDTO(
                                member.getFcmToken(),
                                POST_NOTIFICATION_TITLE,
                                post.getTitle(),
                                NotificationType.POST
                        )));
        return new SavePostResponse();
    }

    public AllPostResponse findAllPost() {
        List<PostResponse> postResponseList = postRepository.findAll().stream()
                .map(post -> PostResponse.of(post))
                .toList();
        return AllPostResponse.of(postResponseList);
    }
}
