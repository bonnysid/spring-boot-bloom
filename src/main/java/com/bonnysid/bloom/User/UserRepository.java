package com.bonnysid.bloom.User;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.email =?1")
    Optional<User> getUserByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.username =?1")
    Optional<User> getUserByUsername(String username);
}
