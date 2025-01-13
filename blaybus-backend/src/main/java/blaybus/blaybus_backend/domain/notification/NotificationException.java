package blaybus.blaybus_backend.domain.notification;

import blaybus.blaybus_backend.global.exception.BaseException;
import blaybus.blaybus_backend.global.exception.ErrorCode;

public class NotificationException extends BaseException {
    public NotificationException(ErrorCode errorCode) {
        super(errorCode);
    }
}
