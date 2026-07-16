package com.buyzone.user_service.controller;

import com.buyzone.user_service.dto.request.UserRequestDto;
import com.buyzone.user_service.dto.response.GenericResponseDto;
import com.buyzone.user_service.dto.response.UserResponseDto;
import com.buyzone.user_service.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {

        this.userService = userService;
    }


    @PostMapping("/adduser")
    public ResponseEntity<UserResponseDto> addUser(@Valid @RequestBody UserRequestDto userRequestDto) {

        UserResponseDto userResponseDto = userService.registerUser(userRequestDto);

        return new ResponseEntity<>(userResponseDto, HttpStatusCode.valueOf(201));

    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {

        return new ResponseEntity<>( userService.getAllUsers() ,  HttpStatusCode.valueOf(200));

    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id) {

        return new ResponseEntity<>( userService.getUserById(id) , HttpStatusCode.valueOf(200));

    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long id , @Valid @RequestBody UserRequestDto userRequestDto) {

        return new ResponseEntity<>( userService.updateUser(userRequestDto , id) ,  HttpStatusCode.valueOf(200));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponseDto> deleteUser(@PathVariable Long id) {

        return new ResponseEntity<>( userService.removeUserById(id) ,  HttpStatusCode.valueOf(200));

    }

}
