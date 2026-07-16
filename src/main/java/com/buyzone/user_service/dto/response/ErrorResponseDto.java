package com.buyzone.user_service.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorResponseDto {

    public ErrorResponseDto(){

        this.timestamp = LocalDateTime.now();

    }

    private boolean success = true;
    private int status;
    private String message;
    private LocalDateTime timestamp;
}
