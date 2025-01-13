package blaybus.blaybus_backend.domain.notification;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FcmNotificationDTO {
    private String fcmToken;
    private String title;
    private String body;
    private NotificationType notificationType;
}
