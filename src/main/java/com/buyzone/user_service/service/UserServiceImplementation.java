package com.buyzone.user_service.service;

import com.buyzone.user_service.dto.response.GenericResponseDto;
import com.buyzone.user_service.dto.request.UserRequestDto;
import com.buyzone.user_service.dto.response.UserResponseDto;
import com.buyzone.user_service.model.User;
import com.buyzone.user_service.reposetory.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImplementation implements UserService{
    @Autowired
    UserRepository userRepository;

    @Override
    public UserResponseDto registerUser(UserRequestDto userRequestDto) {

        User user = mapUserRequestDtoToUser(new User(),userRequestDto );

        userRepository.save(user);

        return mapUserToUserResponse(user, new UserResponseDto());
    }

    @Override
    public UserResponseDto getUserById(Long id) {
        return null;
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        return List.of();
    }

    @Override
    public UserResponseDto updateUser(UserRequestDto userRequestDto) {
        return null;
    }

    @Override
    public GenericResponseDto removeUserById(Long id) {
        return null;
    }

    //Helper method

    private User mapUserRequestDtoToUser (User user , UserRequestDto userRequestDto) {
        user.setPassword(userRequestDto.getPassword());
        user.setName(userRequestDto.getName());
        user.setEmail(userRequestDto.getEmail());
        user.setPhone(userRequestDto.getPhone());
        user.setGender(userRequestDto.getGender());

        return user;

    }

    private UserResponseDto mapUserToUserResponse(User user , UserResponseDto userResponseDto) {
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setName(user.getName());
        userResponseDto.setPhone(user.getPhone());
        userResponseDto.setGender(user.getGender());
        userResponseDto.setId(user.getId());

        return userResponseDto;
    }
}
