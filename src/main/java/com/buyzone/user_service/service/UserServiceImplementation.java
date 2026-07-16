package com.buyzone.user_service.service;

import com.buyzone.user_service.dto.response.GenericResponseDto;
import com.buyzone.user_service.dto.request.UserRequestDto;
import com.buyzone.user_service.dto.response.UserResponseDto;
import com.buyzone.user_service.enums.UserRole;
import com.buyzone.user_service.exception.DuplicateResourceException;
import com.buyzone.user_service.exception.UserNotFoundException;
import com.buyzone.user_service.model.User;
import com.buyzone.user_service.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImplementation implements UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    UserServiceImplementation( UserRepository userRepository , PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;

    }

    @Override
    public UserResponseDto registerUser(UserRequestDto userRequestDto) {

        User user = mapUserRequestDtoToUser(new User(),userRequestDto );

        if(userRepository.existsByEmail(user.getEmail())){
            throw new DuplicateResourceException("Email already exists.");
        }

        if(userRepository.existsByPhone(user.getPhone())){
            throw new DuplicateResourceException("Phone number already exists.");
        }

         user.setPassword(passwordEncoder.encode(user.getPassword()));
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

        if(userRepository.existsByEmail(user.getEmail())){
            throw new DuplicateResourceException("Email already exists.(Duplicate entry found)");
        }

        if(userRepository.existsByPhone(user.getPhone())){
            throw new DuplicateResourceException("Phone number already exists.(Duplicate entry found)");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

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
        genericResponseDto.setSuccess(true);
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
        user.setAddress(userRequestDto.getAddress());
        user.setRoles(userRequestDto.getRoles());

        for (UserRole role : userRequestDto.getRoles()){

            System.out.println(role);
        }


        return user;

    }

    private UserResponseDto mapUserToUserResponseDto(User user , UserResponseDto userResponseDto) {
        userResponseDto.setId(user.getId());
        userResponseDto.setName(user.getName());
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setPhone(user.getPhone());
        userResponseDto.setAddress(user.getAddress());
        userResponseDto.setGender(user.getGender());
        userResponseDto.setRoles(user.getRoles());

        return userResponseDto;
    }
}
