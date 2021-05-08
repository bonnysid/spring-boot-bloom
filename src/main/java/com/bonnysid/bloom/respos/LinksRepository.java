package com.bonnysid.bloom.respos;

import com.bonnysid.bloom.model.Link;
import com.bonnysid.bloom.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LinksRepository extends JpaRepository<Link, Long> {
    @Query(value = "select * from link where user_id = ?1", nativeQuery = true)
    public List<Link> getLinksByUserId(long id);
}
