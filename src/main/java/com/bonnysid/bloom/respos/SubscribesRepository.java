package com.bonnysid.bloom.respos;

import com.amazonaws.services.dynamodbv2.xspec.S;
import com.bonnysid.bloom.model.Post;
import com.bonnysid.bloom.model.Subscribe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubscribesRepository extends JpaRepository<Subscribe, Long> {
    @Query(value = "select * from subscribe where email = ?1 and id_followed_user = ?2", nativeQuery = true)
    public Optional<Subscribe> findSubscribes(String email, long id);

    @Query(value = "select * from subscribe where email = ?1", nativeQuery = true)
    public Optional<List<Subscribe>> findAllSubscribes(String email);
}
