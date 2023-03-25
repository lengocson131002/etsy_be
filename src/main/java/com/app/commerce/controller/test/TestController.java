package com.app.commerce.controller.test;

import com.app.commerce.exception.ApiException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin")
public class TestController {
    @GetMapping
    public ResponseEntity<String> getHello() {
        throw new RuntimeException("Internal exception");
    }
}
