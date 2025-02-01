package com.example.springelkexceptiontracking.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
@RequiredArgsConstructor
public enum ErrorCode {
    USER_NOT_FOUND("%d번의 유저를 찾을 수 없습니다.", HttpStatus.NOT_FOUND);

    private final String message;

    @Getter
    private final HttpStatus status;

    public String getMessage(Object... args){
        return String.format(message, args);
    }
}
