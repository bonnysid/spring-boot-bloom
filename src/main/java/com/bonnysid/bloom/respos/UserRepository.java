package com.bonnysid.bloom.respos;

import com.bonnysid.bloom.model.User;
import com.bonnysid.bloom.model.enums.Status;
import org.joda.time.LocalDate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM users u WHERE u.email =?1")
    Optional<User> getUserByEmail(String email);

    @Query("SELECT u FROM users u WHERE u.username =?1")
    Optional<User> getUserByUsername(String username);

    @Query(value = "SELECT id FROM users WHERE email =?1", nativeQuery = true)
    Optional<Long> getUserIdByEmail(String email);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String username);

    boolean existsById(Long id);

}
