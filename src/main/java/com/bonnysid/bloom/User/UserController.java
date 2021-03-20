package com.bonnysid.bloom.User;

import com.bonnysid.bloom.Post.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/")
public class UserController {
    private UserService userService;

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("profile/photo/{id}")
    public String getPhoto(@PathVariable long id) {
        return userService.getPhoto(id);
    }

    @PutMapping("profile/photo/{id}")
    public void putPhoto(@PathVariable("id") long id, @RequestParam("image") MultipartFile image) {
        System.out.println(image);
        userService.putPhoto(id, image);
    }


    @GetMapping(path = "profile/{id}")
    public User getUser(@PathVariable long id) {
        return userService.getUser(id);
    }

    @PostMapping("/users")
    public void postUser(@RequestBody User user) {
        userService.postUser(user);
    }

    @DeleteMapping(path = "users/{id}")
    public void deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
    }

//    @PutMapping(path = "{id}")
//    public void updateUser(@PathVariable("id") long id, @RequestParam String name, @RequestParam String email) {
//        userService.updateUser(id, name, email);
//    }

    @PutMapping(path = "profile/{id}")
    public void updateUserByJSON(@PathVariable("id") long id, @RequestBody User user) {
        userService.updateUser(id, user);
    }
}
