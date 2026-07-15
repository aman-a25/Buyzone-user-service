package com.buyzone.user_service.controller;

import com.buyzone.user_service.dto.request.UserRequestDto;
import com.buyzone.user_service.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {

        this.userService = userService;
    }


    @PostMapping
    public ResponseEntity<?> addUser(@Valid @RequestBody UserRequestDto userRequestDto) {

        userService.registerUser(userRequestDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
