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

    public String getAuthEmail() {
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() == "anonymousUser") return null;
        return ((UserDetails) (SecurityContextHolder.getContext().getAuthentication().getPrincipal())).getUsername();
    }

    public String getAuthUsername() {
        return userRepository.getUserByEmail(getAuthEmail()).orElseThrow(() -> new IllegalStateException("User doesn't exists!")).getUsername();
    }

    public Long getAuthId() {
        System.out.println(getAuthEmail());
        return userRepository.getUserIdByEmail(getAuthEmail()).orElseThrow(() -> new IllegalStateException("User doesn't exists!"));
    }
}
