package com.poseiden.configuration.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ResponseBody
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResult> handleException(Exception e) {
        log.error(e.getMessage(), e);
        if (e instanceof BusinessException) {
            BusinessException exception = (BusinessException) e;

            return new ResponseEntity<>(new ErrorResult(exception.getErrorKey().getValue()), exception.getHttpStatus());
        }
        return new ResponseEntity<>(new ErrorResult(INTERNAL_SERVER_ERROR.getReasonPhrase()), BAD_REQUEST);
    }

    @Getter
    @AllArgsConstructor
    private static
    class ErrorResult {
        private String errorPhase;
    }
}
