package com.buyzone.user_service.dto.response;

import lombok.Data;

@Data
public class GenericResponseDto {
    private String message;
    private String status;
    private Boolean success;
    private Object data;
}
