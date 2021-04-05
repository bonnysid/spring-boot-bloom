package com.bonnysid.bloom.security;

import com.bonnysid.bloom.respos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthInfo {
    private final UserRepository userRepository;

    @Autowired
    public AuthInfo(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String getAuthUsername() {
        return ((UserDetails) (SecurityContextHolder.getContext().getAuthentication().getPrincipal())).getUsername();
    }

    public Long getAuthId() {
        return userRepository.getUserIdByUsername(getAuthUsername()).orElseThrow(() -> new IllegalStateException("User doesn't exists!"));
    }
}
