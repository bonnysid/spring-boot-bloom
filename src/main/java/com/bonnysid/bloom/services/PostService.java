package com.bonnysid.bloom.services;

import com.bonnysid.bloom.model.MessageResponse;
import com.bonnysid.bloom.model.Post;
import com.bonnysid.bloom.respos.PostRepository;
import com.bonnysid.bloom.security.AuthInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private PostRepository postRepository;
    private AuthInfo authInfo;

    @Autowired
    public PostService(PostRepository postRepository, AuthInfo authInfo) {
        this.postRepository = postRepository;
        this.authInfo = authInfo;
    }

    public List<Post> getPosts(long id) {
        return postRepository.getPostsByUserId(id);
    }

    public ResponseEntity<?> createPost(Post post) {
        if(post.getTitle().length() == 0 || post.getText().length() == 0) return ResponseEntity
                .badRequest().body(new MessageResponse("Title or text invalid!"));
        else {
            post.setUserId(authInfo.getAuthId());
            postRepository.save(post);
            return ResponseEntity.ok().body(post);
        }
    }
}
