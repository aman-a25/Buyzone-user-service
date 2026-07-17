package com.buyzone.user_service.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorResponseDto {

    public ErrorResponseDto(int status, String message){

        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.message = message;
    }

    private boolean success = true;
    private int status;
    private String message;
    private LocalDateTime timestamp;
}
