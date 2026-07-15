package com.buyzone.user_service.controller;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class ServerController {
    @GetMapping
    public ResponseEntity<String> getServerStatus() {
        return new ResponseEntity<>("Buyzone - user-service is live!", HttpStatusCode.valueOf(200));
    }

    @PostMapping
    public ResponseEntity<String> getServerStatusPost() {
        return new ResponseEntity<>("Buyzone - user-service is live! (with security)", HttpStatusCode.valueOf(200));
    }


}
