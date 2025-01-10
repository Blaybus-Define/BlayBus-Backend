package blaybus.blaybus_backend.domain.member.exception;

import blaybus.blaybus_backend.global.exception.BaseException;
import blaybus.blaybus_backend.global.exception.ErrorCode;

public class MemberException extends BaseException {
    public MemberException(ErrorCode errorCode) {
        super(errorCode);
    }
}
