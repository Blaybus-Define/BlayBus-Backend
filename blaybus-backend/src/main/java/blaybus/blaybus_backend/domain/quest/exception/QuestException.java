package blaybus.blaybus_backend.domain.quest.exception;

import blaybus.blaybus_backend.global.exception.BaseException;
import blaybus.blaybus_backend.global.exception.ErrorCode;

public class QuestException extends BaseException {

    public QuestException(ErrorCode errorCode) {
        super(errorCode);
    }
}
