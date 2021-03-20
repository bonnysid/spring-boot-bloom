package com.bonnysid.bloom.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
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

    public User getUser(long id) {
        return userRepository.findById(id).orElseThrow(() -> new IllegalStateException("User with id \" + id + \" doesn't exists!"));
    }

    public void postUser(User user) {
        Optional<User> userByEmail = userRepository.getUserByEmail(user.getEmail());
        Optional<User> userByUsername = userRepository.getUserByUsername(user.getUsername());
        if (userByEmail.isPresent()) throw new IllegalStateException("Email is taken");
        if (userByUsername.isPresent()) throw new IllegalStateException("Username is taken");
        userRepository.save(user);
    }

    public void deleteUser(long id) {
        boolean userExists = userRepository.existsById(id);
        if (userExists) userRepository.deleteById(id);
        else throw new IllegalStateException("User with id " + id + " doesn't exists!");
    }

    @Transactional
    public void updateUser(long id, String name, String email) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("User with id \" + id + \" doesn't exists!"));

        if (name != null && !Objects.equals(user.getUsername(), name)) {
            if(userRepository.getUserByUsername(name).isPresent()) throw new IllegalStateException("Username is taken");
            user.setUsername(name);
        }

        if (email != null && !Objects.equals(user.getEmail(), email)) {
            if(userRepository.getUserByEmail(email).isPresent()) throw new IllegalStateException("Email is taken");
            user.setEmail(email);
        }
    }
    @Transactional
    public void updateUser(long id, User u) {
        updateUser(id, u.getUsername(), u.getEmail());
    }
}
