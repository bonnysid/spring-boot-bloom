package com.bonnysid.bloom.respos;

import com.bonnysid.bloom.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(value = "select * from posts where user_id = ?1", nativeQuery = true)
    public List<Post> getPostsByUserId(long id);
}
