package blaybus.blaybus_backend.domain.notification.service;

import blaybus.blaybus_backend.domain.notification.dto.FcmNotificationDTO;
import blaybus.blaybus_backend.domain.notification.exception.NotificationException;
import blaybus.blaybus_backend.global.exception.ErrorCode;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    public String sendNotification(FcmNotificationDTO notificationDTO) {
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
            return FirebaseMessaging.getInstance().send(message);
        } catch (Exception e) {
            throw new NotificationException(ErrorCode.FAILED_MESSAGE_SEND);
        }
    }
}
