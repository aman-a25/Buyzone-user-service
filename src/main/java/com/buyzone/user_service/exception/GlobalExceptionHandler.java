package com.buyzone.user_service.exception;

import com.buyzone.user_service.dto.response.ErrorResponseDto;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import static org.hibernate.internal.util.collections.ArrayHelper.forEach;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(IdentifierNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleIdentifierNotFoundException(IdentifierNotFoundException e) {
        ErrorResponseDto erd = new ErrorResponseDto(404, e.getMessage());

        return new ResponseEntity<>(erd, HttpStatusCode.valueOf(404));
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ErrorResponseDto> handleDuplicateResourceException(DuplicateResourceException e) {
        ErrorResponseDto erd = new ErrorResponseDto(409, e.getMessage());

        return new ResponseEntity<>(erd, HttpStatusCode.valueOf(409));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleUserNotFoundException(UserNotFoundException e) {

        ErrorResponseDto erd = new ErrorResponseDto(404, e.getMessage());

        return new ResponseEntity<>(erd, HttpStatusCode.valueOf(404));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorResponseDto>> handleValidationExceptions(MethodArgumentNotValidException ex) {

        List<ErrorResponseDto> errors = new LinkedList<>();

        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

        for (FieldError error : fieldErrors) {

            // Extract the data from Spring's error object
            String brokenField = error.getField();
            String defaultMessage = error.getDefaultMessage(); // e.g., "must be a well-formed email address"

            // 3. Create and populate your custom DTO
            ErrorResponseDto errorDto = new ErrorResponseDto(400,brokenField + " : " + defaultMessage);

            // 4. Add it to your LinkedList
            errors.add(errorDto);
        }



        // Return 400 Bad Request with the clean map of errors
        return new ResponseEntity<>(errors, HttpStatusCode.valueOf(400));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleException(Exception e) {

        ErrorResponseDto erd = new ErrorResponseDto(400, e.getMessage());

        return new ResponseEntity<>(erd, HttpStatusCode.valueOf(400));
    }
}
