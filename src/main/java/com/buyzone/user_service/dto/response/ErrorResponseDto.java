package com.buyzone.user_service.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorResponseDto {

    private boolean success = true;
    private int status;
    private String message;
    private LocalDateTime timestamp;
}
