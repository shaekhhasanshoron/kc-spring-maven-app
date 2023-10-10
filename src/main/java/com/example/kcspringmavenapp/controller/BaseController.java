package com.example.kcspringmavenapp.controller;

import com.example.kcspringmavenapp.utilities.ResponseData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {

    @GetMapping("/")
    public ResponseEntity<?> getApi() {
        return ResponseData.sendData(HttpStatus.OK, "Live", "Welcome to KC Spring Maven App");
    }

    @GetMapping("/health")
    public ResponseEntity<?> getHealth() {
        return ResponseData.sendData(HttpStatus.OK, "Live", "Application is running....");
    }
}
