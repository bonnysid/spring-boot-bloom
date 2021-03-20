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

    @GetMapping(path = "{id}")
    public User getUser(@PathVariable long id) {
        return userService.getUser(id);
    }

    @PostMapping
    public void postUser(@RequestBody User user) {
        userService.postUser(user);
    }

    @DeleteMapping(path = "{id}")
    public void deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
    }

//    @PutMapping(path = "{id}")
//    public void updateUser(@PathVariable("id") long id, @RequestParam String name, @RequestParam String email) {
//        userService.updateUser(id, name, email);
//    }

    @PutMapping(path = "{id}")
    public void updateUserByJSON(@PathVariable("id") long id, @RequestBody User user) {
        userService.updateUser(id, user);
    }
}
