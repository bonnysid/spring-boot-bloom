package com.bonnysid.bloom.security;

import com.bonnysid.bloom.respos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getPrincipal() == "anonymousUser") return null;
        return ((UserDetails) (authentication.getPrincipal())).getUsername();
    }

    public String getAuthEmail() {
        return userRepository.getUserByUsername(getAuthUsername()).orElseThrow(() -> new IllegalStateException("User doesn't exists!")).getEmail();
    }

    public Long getAuthId() {
        return userRepository.getUserByUsername(getAuthUsername()).orElseThrow(() -> new IllegalStateException("User doesn't exists!")).getId();
    }
}
