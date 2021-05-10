package com.bonnysid.bloom.respos;

import com.bonnysid.bloom.model.Role;
import com.bonnysid.bloom.model.enums.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(Roles name);
    @Query(value = "insert into user_roles(user_id, role_id) values (?1, ?2)", nativeQuery = true)
    void saveRole(Long userId, Long roleId);
}
