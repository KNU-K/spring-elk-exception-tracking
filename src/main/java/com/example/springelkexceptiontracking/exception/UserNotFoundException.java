package com.example.springelkexceptiontracking.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UserNotFoundException extends BusinessException{
    public UserNotFoundException(Long id) {
        super(ErrorCode.USER_NOT_FOUND, id);
    }
}
