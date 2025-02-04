package com.emoney.comm.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.method.ParameterValidationResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class EmoneyExceptionHandler {

    @ExceptionHandler(EmoneyException.class)
    public ResponseEntity<String> handleEmoneyException(final EmoneyException e) {
        return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(final IllegalArgumentException e) {
        return ResponseEntity.status(500).body(e.getMessage());
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> handleNullPointerException(final NullPointerException e) {
        return ResponseEntity.status(500).body(e.getMessage());
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<String> handleHandlerMethodValidationException(final HandlerMethodValidationException e) {
        String msg = e.getAllValidationResults().stream()
            .map(ParameterValidationResult::toString)
            .collect(Collectors.joining("\n"));

        return ResponseEntity.status(400).body(msg);
    }

    // 일반적인 예외 처리
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(final Exception e) {
        return ResponseEntity.status(500).body(e.getMessage());
    }
}
