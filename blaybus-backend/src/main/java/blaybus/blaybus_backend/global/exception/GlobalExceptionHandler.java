package blaybus.blaybus_backend.global.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler(RuntimeException.class)
    public String handleRuntimeException(RuntimeException ex) {

        log.warn("{}", ex.getMessage());
        return ex.getMessage();
    }


}
