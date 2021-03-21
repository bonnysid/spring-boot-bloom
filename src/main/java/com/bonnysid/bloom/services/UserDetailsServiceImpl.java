package com.bonnysid.bloom.services;

import com.bonnysid.bloom.model.User;
import com.bonnysid.bloom.respos.UserRepository;
import com.bonnysid.bloom.security.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository repository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User u = repository.getUserByEmail(email).orElseThrow(() -> new IllegalStateException("User not found"));
        return SecurityUser.fromUser(u);
    }
}
