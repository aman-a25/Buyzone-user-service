package com.buyzone.user_service.service;

import com.buyzone.user_service.dto.response.GenericResponseDto;
import com.buyzone.user_service.dto.request.UserRequestDto;
import com.buyzone.user_service.dto.response.UserResponseDto;

import java.util.List;

public interface UserService {

    UserResponseDto registerUser(UserRequestDto userRequestDto);
    UserResponseDto getUserById(Long id);
    List<UserResponseDto> getAllUsers();
    UserResponseDto updateUser(UserRequestDto userRequestDto, Long id);
    GenericResponseDto removeUserById(Long id);

}
