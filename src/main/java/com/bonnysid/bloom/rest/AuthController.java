package com.bonnysid.bloom.rest;

import com.bonnysid.bloom.model.JwtResponse;
import com.bonnysid.bloom.model.LoginRequest;
import com.bonnysid.bloom.model.User;
import com.bonnysid.bloom.model.view.Me;
import com.bonnysid.bloom.respos.UserRepository;
import com.bonnysid.bloom.security.JwtTokenProvider;
import com.bonnysid.bloom.security.UserDetailsImpl;
import com.bonnysid.bloom.services.UserDetailsServiceImpl;
import com.bonnysid.bloom.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/1.0/auth")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private JwtTokenProvider jwtTokenProvider;
    private UserService userService;
    private PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository, JwtTokenProvider jwtTokenProvider, UserService userService, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<?> authenticate(@RequestBody LoginRequest loginRequest) {
//        try {
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
//            User user = userRepository.getUserByEmail(request.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User doesn't exists"));
//            String token = jwtTokenProvider.createToken(request.getEmail(), user.getRole().);
//            Map<Object, Object> response = new HashMap<>();
//            response.put("email", request.getEmail());
//            response.put("token", token);
//            return ResponseEntity.ok(response);
//        } catch (AuthenticationException e) {
//            return new ResponseEntity<>("Invalid email/password combination", HttpStatus.FORBIDDEN);
//        }
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenProvider.createToken(authentication);

        UserDetailsImpl userDetailsImpl = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetailsImpl.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(
                jwt,
                userDetailsImpl.getId(),
                userDetailsImpl.getUsername(),
                userDetailsImpl.getEmail(),
                roles));
    }

    @PreAuthorize("hasAuthority('user:read')")
    @PostMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, null);
    }

    @PreAuthorize("hasAuthority('user:read')")
    @GetMapping("/me")
    public Me getMe() {
        return userService.getMe();
    }
}
