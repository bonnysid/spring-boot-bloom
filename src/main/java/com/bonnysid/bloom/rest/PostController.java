package com.bonnysid.bloom.rest;

import com.bonnysid.bloom.services.PostService;
import com.bonnysid.bloom.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/users/posts")
public class PostController {

    private PostService postService;

    @Autowired

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping(path = "{id}")
    @PreAuthorize("hasAuthority('user:read')")
    public List<Post> getUsers(@PathVariable long id) {
        return postService.getPosts(id);
    }
}
