package com.devjck.springboard.config.result.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    USER_NOT_FOUND(NOT_FOUND, "해당 유저를 찾을 수 없습니다."),
    ALREADY_EXISTS_EMAIL(BAD_REQUEST, "이미 존재하는 이메일입니다.");


    private final HttpStatus httpStatus;
    private final String detailMessage;
}
