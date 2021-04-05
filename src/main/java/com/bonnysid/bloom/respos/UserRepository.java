package com.bonnysid.bloom.respos;

import com.bonnysid.bloom.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.email =?1")
    Optional<User> getUserByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.username =?1")
    Optional<User> getUserByUsername(String username);

    @Query(value = "SELECT id FROM users WHERE username =?1", nativeQuery = true)
    Optional<Long> getUserIdByUsername(String username);
}
