package com.bonnysid.bloom.services;

import com.bonnysid.bloom.model.JwtResponse;
import com.bonnysid.bloom.model.MessageResponse;
import com.bonnysid.bloom.model.Role;
import com.bonnysid.bloom.model.User;
import com.bonnysid.bloom.model.enums.Roles;
import com.bonnysid.bloom.model.view.Me;
import com.bonnysid.bloom.respos.RoleRepository;
import com.bonnysid.bloom.respos.UserRepository;
import com.bonnysid.bloom.security.JwtTokenProvider;
import com.bonnysid.bloom.security.UserDetailsImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthService {
    private final UserService userService;
    private final RoleRepository roleRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    public AuthService(UserService userService, RoleRepository roleRepository, JwtTokenProvider jwtTokenProvider, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, UserRepository userRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
    }

    public ResponseEntity<?> login(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenProvider.createToken(authentication);

        UserDetailsImpl userDetailsImpl = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetailsImpl.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(
                jwt,
                userDetailsImpl.getId(),
                userDetailsImpl.getUsername(),
                userDetailsImpl.getEmail(),
                roles));
    }

    public ResponseEntity<?> signUp (String username, String email, String password, Set<String> rolesRequest) {
        if(userRepository.existsByUsername(username)) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if(userRepository.existsByUsername(username)) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already taken!"));
        }

        User user = new User(username, email, passwordEncoder.encode(password));

        Set<Role> roles = new HashSet<>();
        Set<String> strRoles = rolesRequest;
        if(strRoles.size() == 0) {
            Role userRole = roleRepository.findByName(Roles.USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(Roles.ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(Roles.MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);
                        break;
                    default:
                        Role userRole = roleRepository.findByName(Roles.USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                        break;
                }
            });
        }
        user.setRoles(roles);
        userRepository.save(user);
//        roleRepository.saveRole(userId, 1L);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    public ResponseEntity<?> getMe() {
        try {
            Me data = userService.getMe();
            return ResponseEntity.ok().body(data);
        } catch (Error error) {
            return ResponseEntity.badRequest().body(error);
        }
    }
}
