package com.bonnysid.bloom.rest;

import com.bonnysid.bloom.model.view.UserView;
import com.bonnysid.bloom.model.view.UserListView;
import com.bonnysid.bloom.services.UserService;
import com.bonnysid.bloom.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/1.0/")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("users")
    @PreAuthorize("hasAuthority('USER')")
    public List<UserListView> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("friends")
    @PreAuthorize("hasAuthority('USER')")
    public List<UserListView> getFriends() { return userService.getFriends();}

    @GetMapping("profile/photo/{id}")
    @PreAuthorize("hasAuthority('user:read')")
    public byte[] getPhoto(@PathVariable long id) {
        return userService.getPhoto(id);
    }

    @PutMapping(value = "profile/photo/{id}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('user:write')")
    public void putPhoto(@PathVariable("id") long id, @RequestParam("image") MultipartFile image) {
        userService.putPhoto(id, image);
    }


    @GetMapping(path = "profile/{id}")
    public UserView getUser(@PathVariable long id) {
        return userService.getUser(id);
    }

//    @PostMapping("users")
//    @PreAuthorize("hasAuthority('user:write')")
//    public User postUser(@RequestBody User user) {
//        userService.postUser(user);
//        return user;
//    }

    @DeleteMapping(path = "users/{id}")
    @PreAuthorize("hasAuthority('user:write')")
    public void deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
    }

    @PutMapping(path = "profile/{id}")
    @PreAuthorize("hasAuthority('user:write')")
    public void updateUserByJSON(@PathVariable("id") long id, @RequestBody User user) {
        userService.updateUser(id, user);
    }
}
