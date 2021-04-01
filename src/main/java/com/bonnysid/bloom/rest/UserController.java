package com.bonnysid.bloom.rest;

import com.bonnysid.bloom.model.UserView;
import com.bonnysid.bloom.model.UserViewForUserList;
import com.bonnysid.bloom.services.UserService;
import com.bonnysid.bloom.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('user:read')")
    public List<UserViewForUserList> getUsers() {
        return userService.getUsers();
    }

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
        System.out.println(image);
        userService.putPhoto(id, image);
    }


    @GetMapping(path = "profile/{id}")
    @PreAuthorize("hasAuthority('user:read')")
    public UserView getUser(@PathVariable long id) {
        return userService.getUser(id);
    }

    @PostMapping("/users")
    @PreAuthorize("hasAuthority('user:write')")
    public User postUser(@RequestBody User user) {
        userService.postUser(user);
        return user;
    }

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
