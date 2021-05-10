package com.bonnysid.bloom.rest;

import com.bonnysid.bloom.model.*;
import com.bonnysid.bloom.model.enums.Roles;
import com.bonnysid.bloom.model.view.Me;
import com.bonnysid.bloom.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@RestController
@RequestMapping("/api/1.0/auth")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest.getUsername(), loginRequest.getPassword());
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, null);
    }


    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpRequest request) {
        return authService.signUp(request.getUsername(), request.getEmail(), request.getPassword(), request.getRole());
    }


    @GetMapping("/me")
    public ResponseEntity<?> getMe() {
        return authService.getMe();
    }
}
