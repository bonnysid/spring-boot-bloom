package com.bonnysid.bloom.services;

import com.bonnysid.bloom.model.MessageResponse;
import com.bonnysid.bloom.model.Post;
import com.bonnysid.bloom.model.view.PostView;
import com.bonnysid.bloom.model.view.UserListView;
import com.bonnysid.bloom.respos.PostRepository;
import com.bonnysid.bloom.security.AuthInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {
    private PostRepository postRepository;
    private AuthInfo authInfo;
    private UserService userService;

    @Autowired
    public PostService(PostRepository postRepository, AuthInfo authInfo, UserService userService) {
        this.postRepository = postRepository;
        this.authInfo = authInfo;
        this.userService = userService;
    }

    public List<PostView> getPosts(long id) {
        List<Post> posts = postRepository.getPostsByUserId(id);
        UserListView userListView = new UserListView(userService.getUserOrElseThrow(id));
        return posts.stream()
                .map(post -> new PostView(post, userListView))
                .collect(Collectors.toList());
    }

    public ResponseEntity<?> createPost(Post post) {
        try {
            if(post.getTitle().length() == 0 || post.getText().length() == 0) return ResponseEntity
                    .badRequest().body(new MessageResponse("Title or text invalid!"));
            else {
                post.setUserId(authInfo.getAuthId());
                Post newPost = postRepository.save(post);
                UserListView user = new UserListView(userService.getUserOrElseThrow(authInfo.getAuthId()));
                return ResponseEntity.ok().body(new PostView(newPost, user));
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    public ResponseEntity<?> deletePost(Long id) {
        try {
            postRepository.deleteById(id);
            return ResponseEntity.ok().body(true);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    @Transactional
    public ResponseEntity<?> likePost(Long id) {
        try {
            Post post = getPostOrElseThrow(id);
            post.setLikeCount(post.getLikeCount() + 1);
            return ResponseEntity.ok().body(true);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    @Transactional
    public ResponseEntity<?> unlikePost(Long id) {
        try {
            Post post = getPostOrElseThrow(id);
            post.setLikeCount(post.getLikeCount() - 1);
            return ResponseEntity.ok().body(true);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    public Post getPostOrElseThrow(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Post doesn't exists!"));
    }
}
