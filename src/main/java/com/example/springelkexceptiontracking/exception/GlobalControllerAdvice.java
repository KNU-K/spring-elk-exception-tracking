package com.example.springelkexceptiontracking.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalControllerAdvice {
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<String> handleNullPointerException(BusinessException ex) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), ex.getStatus());
    }

}
