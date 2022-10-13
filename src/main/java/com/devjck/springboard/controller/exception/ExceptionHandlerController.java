package com.devjck.springboard.controller.exception;

import com.devjck.springboard.config.result.exception.ErrorManagement;
import com.devjck.springboard.config.result.exception.ErrorResponse;
import com.devjck.springboard.config.result.exception.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestController
@RestControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ConstraintViolationException.class, DataIntegrityViolationException.class})
    protected ResponseEntity<ErrorResponse> handleDataException() {
        log.error("handleDataException throw Exception : {}","test" );
        return ErrorResponse.toResponseEntity(ErrorCode.USER_NOT_FOUND);
    }

    @ExceptionHandler(value = { ErrorManagement.class })
    protected ResponseEntity<ErrorResponse> handleCustomException(ErrorManagement e) {
        log.error("handleCustomException throw CustomException : {}", e.getErrorCode());
        return ErrorResponse.toResponseEntity(e.getErrorCode());
    }
}
