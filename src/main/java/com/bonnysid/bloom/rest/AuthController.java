package com.bonnysid.bloom.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth/")
public class AuthController {

    public ResponseEntity<?> authenticate() {
        return null;
    }

    @PostMapping("logout")
    public void logout() {

    }
}
