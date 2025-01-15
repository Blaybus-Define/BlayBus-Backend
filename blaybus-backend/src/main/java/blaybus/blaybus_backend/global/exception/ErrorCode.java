package blaybus.blaybus_backend.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "잘못된 비민번호입니다."),
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 회원입니다."),
    DUPLICATE_EMPLOYEE_NUMBER(HttpStatus.CONFLICT, "이미 존재하는 회원의 사번입니다."),
    UNAUTHORIZED_USER(HttpStatus.UNAUTHORIZED, "인증되지 않은 사용자입니다."),
    INVALID_JOB_ROLE(HttpStatus.BAD_REQUEST, "잘못된 직군입니다."),

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버에 문제가 발생했습니다"),
    FAILED_MESSAGE_SEND(HttpStatus.INTERNAL_SERVER_ERROR, "알림 메세지 보내기를 실패했습니다"),
    ALREADY_COMPLETED(HttpStatus.BAD_REQUEST, "이미 완료 처리된 퀘스트입니다");

    private final HttpStatus httpStatus;
    private final String message;

}
