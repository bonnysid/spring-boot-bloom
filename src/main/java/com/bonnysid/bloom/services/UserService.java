package com.bonnysid.bloom.services;

import com.bonnysid.bloom.model.*;
import com.bonnysid.bloom.model.enums.Roles;
import com.bonnysid.bloom.model.enums.Status;
import com.bonnysid.bloom.respos.LinksRepository;
import com.bonnysid.bloom.respos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final LinksRepository linksRepository;

    @Autowired
    public UserService(UserRepository userRepository, LinksRepository linksRepository) {
        this.userRepository = userRepository;
        this.linksRepository = linksRepository;
    }

    public List<UserViewForUserList> getUsers() {
        return userRepository.findAll().stream()
                .map(UserViewForUserList::new)
                .collect(Collectors.toList());
    }

    public UserView getUser(Long id) {
        return new UserView(userRepository.findById(id).orElseThrow(() -> new IllegalStateException("User with id \" + id + \" doesn't exists!")));
    }

    public void postUser(User user) {
        Optional<User> userByEmail = userRepository.getUserByEmail(user.getEmail());
        Optional<User> userByUsername = userRepository.getUserByUsername(user.getUsername());
        if (userByEmail.isPresent()) throw new IllegalStateException("Email is taken");
        if (userByUsername.isPresent()) throw new IllegalStateException("Username is taken");
        user.setRole(Roles.USER);
        user.setStatus(Status.ACTIVE);
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
            if (userRepository.getUserByUsername(name).isPresent())
                throw new IllegalStateException("Username is taken");
            user.setUsername(name);
        }

        if (email != null && !Objects.equals(user.getEmail(), email)) {
            if (userRepository.getUserByEmail(email).isPresent()) throw new IllegalStateException("Email is taken");
            user.setEmail(email);
        }
    }

    @Transactional
    public void updateUser(long id, User u) {
        updateUser(id, u.getUsername(), u.getEmail());
    }

    public String getPhoto(long id) {
        return userRepository.findById(id).orElseThrow(() -> new IllegalStateException("User with id \" + id + \" doesn't exists!")).getPhoto();
    }

    public void putPhoto(long id, MultipartFile image) {
        Path fileNameAndPath = Paths.get("/photos", image.getOriginalFilename());
        try {
            Files.write(fileNameAndPath, image.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
