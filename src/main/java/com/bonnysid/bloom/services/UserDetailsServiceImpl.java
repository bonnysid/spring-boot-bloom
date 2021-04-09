package com.bonnysid.bloom.services;

import com.bonnysid.bloom.model.User;
import com.bonnysid.bloom.respos.UserRepository;
import com.bonnysid.bloom.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(username);
        User user = userRepository.getUserByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("User doesn't exists"));
        return UserDetailsImpl.build(user);
    }
}
