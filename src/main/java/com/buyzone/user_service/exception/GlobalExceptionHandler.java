package com.buyzone.user_service.exception;

import com.buyzone.user_service.dto.response.GenericResponseDto;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GenericResponseDto> handleException(Exception e) {

        GenericResponseDto response = new GenericResponseDto();
        response.setMessage(e.getMessage());
        response.setSuccess(Boolean.FALSE);
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(400));
    }
}
