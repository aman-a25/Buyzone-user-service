package com.buyzone.user_service.controller;

import com.buyzone.user_service.dto.request.UserRequestDto;
import com.buyzone.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class ServerController {

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<?> getServerStatus() {
        return new ResponseEntity<>(HttpStatus.OK);


    }

    @PostMapping
    public ResponseEntity<?> addUser(@RequestBody UserRequestDto userRequestDto) {

        userService.registerUser(userRequestDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
