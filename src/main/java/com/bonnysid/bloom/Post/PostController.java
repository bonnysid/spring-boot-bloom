package com.bonnysid.bloom.Post;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("user/posts/")
public class PostController {

    @GetMapping(path = "{userId}")
    public List<Post> getPosts(@PathVariable long id) {

    }
}
