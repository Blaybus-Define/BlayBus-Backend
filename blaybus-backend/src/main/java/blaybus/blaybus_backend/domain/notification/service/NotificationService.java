package blaybus.blaybus_backend.domain.notification.service;

import blaybus.blaybus_backend.domain.notification.dto.FcmNotificationDTO;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationService {
    public void sendNotification(FcmNotificationDTO notificationDTO) {
        try {
            // 메시지 구성
            Message message = Message.builder()
                    .setToken(notificationDTO.getFcmToken()) // 대상 토큰
                    .setNotification(Notification.builder()
                            .setTitle(notificationDTO.getTitle()) // 알림 제목
                            .setBody(notificationDTO.getBody())
                            .build()
                    )
                    .build();

            // 메시지 전송
            FirebaseMessaging.getInstance().send(message);
        } catch (Exception e) {
            log.warn("[알림 실패]: {} ({})", e.getClass().getSimpleName(), e.getMessage());
        }
    }
}
