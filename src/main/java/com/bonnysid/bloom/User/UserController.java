package com.bonnysid.bloom.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @PostMapping
    public void postUser(@RequestBody User user) {
        userService.postUser(user);
    }

    @DeleteMapping(path = "{id}")
    public void deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
    }
}
