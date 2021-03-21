package com.bonnysid.bloom.services;

import com.bonnysid.bloom.model.Post;
import com.bonnysid.bloom.respos.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getPosts(long id) {
        return postRepository.getPostsByUserId(id);
    }

}
