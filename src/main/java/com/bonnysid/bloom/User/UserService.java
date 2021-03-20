package com.bonnysid.bloom.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public void postUser(User user) {
        Optional<User> userByEmail = userRepository.getUserByEmail(user.getEmail());
        Optional<User> userByUsername = userRepository.getUserByUsername(user.getUsername());
        if(userByEmail.isPresent()) throw new IllegalStateException("Email is taken");
        if(userByUsername.isPresent()) throw new IllegalStateException("Username is taken");
        userRepository.save(user);
    }

    public void deleteUser(long id) {
        boolean userExists = userRepository.existsById(id);
        if(userExists) userRepository.deleteById(id);
        else throw new IllegalStateException("User with id " + id + " doesn't exists!");
    }
}
