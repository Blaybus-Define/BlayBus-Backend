package blaybus.blaybus_backend.domain.notification.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String type; // 알림 종류 (예: 경험치, 게시글)

    @Column(nullable = false)
    private String content; // 알림 내용
}
