package com.devjck.springboard.config.result.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorManagement extends RuntimeException{
    private final ErrorCode errorCode;
}
