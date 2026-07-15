package com.buyzone.user_service.service;

import com.buyzone.user_service.dto.response.GenericResponseDto;
import com.buyzone.user_service.dto.request.UserRequestDto;
import com.buyzone.user_service.dto.response.UserResponseDto;
import com.buyzone.user_service.exception.UserNotFoundException;
import com.buyzone.user_service.model.User;
import com.buyzone.user_service.reposetory.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.autoconfigure.WebMvcProperties;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImplementation implements UserService{

    UserRepository userRepository;

    @Autowired
    UserServiceImplementation( UserRepository userRepository) {

        this.userRepository = userRepository;

    }

    @Override
    public UserResponseDto registerUser(UserRequestDto userRequestDto) {

        User user = mapUserRequestDtoToUser(new User(),userRequestDto );

        userRepository.save(user);

        return mapUserToUserResponseDto(user, new UserResponseDto());
    }

    @Override
    public UserResponseDto getUserById(Long id) {

        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User id " + id + " doesn't exist"));

        return mapUserToUserResponseDto(user, new UserResponseDto());

    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        List<User> users = userRepository.findAll();

        List<UserResponseDto> userResponseDtos = new ArrayList<>();

        for(User user : users){

            userResponseDtos.add(mapUserToUserResponseDto(user, new UserResponseDto()));

        }

        return userResponseDtos;

    }

    @Override
    public UserResponseDto updateUser(UserRequestDto userRequestDto , Long id) {
        // checking if user is present or not

        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User id " + id + " doesn't exist"));

        // feeding new values (replacing old ones)
        mapUserRequestDtoToUser(user , userRequestDto);

        user.setId(id);

        User savedUser = userRepository.save(user);

        return mapUserToUserResponseDto( savedUser, new UserResponseDto());



    }

    @Override
    public GenericResponseDto removeUserById(Long id) {

        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User id " + id + " doesn't exist"));

        userRepository.delete(user);

        GenericResponseDto genericResponseDto = new GenericResponseDto();
        genericResponseDto.setMessage("User with id " + id + " has been removed");
        genericResponseDto.setSuccess(true );
        genericResponseDto.setStatus("User Deleted");

        return genericResponseDto;
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

    private UserResponseDto mapUserToUserResponseDto(User user , UserResponseDto userResponseDto) {
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setName(user.getName());
        userResponseDto.setPhone(user.getPhone());
        userResponseDto.setGender(user.getGender());
        userResponseDto.setId(user.getId());

        return userResponseDto;
    }
}
