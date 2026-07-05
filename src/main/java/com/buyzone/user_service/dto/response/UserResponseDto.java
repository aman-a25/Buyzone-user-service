package com.buyzone.user_service.dto.response;

import com.buyzone.user_service.enums.Gender;
import jakarta.persistence.*;
import lombok.Data;

@Data
public class UserResponseDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private Gender gender;

}
