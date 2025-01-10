package blaybus.blaybus_backend.domain.auth.exception;

import blaybus.blaybus_backend.global.exception.BaseException;
import blaybus.blaybus_backend.global.exception.ErrorCode;

public class AuthException extends BaseException {
    public AuthException(ErrorCode errorCode) {
        super(errorCode);
    }
}
