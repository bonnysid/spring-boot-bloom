package com.bonnysid.bloom.rest;

import com.bonnysid.bloom.model.view.PostView;
import com.bonnysid.bloom.services.PostService;
import com.bonnysid.bloom.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/1.0/users/posts")
@CrossOrigin(origins = "http://localhost:3000")
public class PostController {

    private PostService postService;

    @Autowired

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping(path = "{id}")
    @PreAuthorize("hasAuthority('USER')")
    public List<PostView> getPosts(@PathVariable long id) {
        return postService.getPosts(id);
    }

    @PostMapping()
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<?> createPost(@RequestBody Post post) {
        return postService.createPost(post);
    }

    @PostMapping("{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<?> likePost(@PathVariable Long id) {
        return postService.likePost(id);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<?> unlikePost(@PathVariable Long id) {
        return postService.unlikePost(id);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<?> deletePost(@PathVariable Long id) { return postService.deletePost(id); }
}
