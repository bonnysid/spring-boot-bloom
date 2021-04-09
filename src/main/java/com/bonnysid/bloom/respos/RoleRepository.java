package com.bonnysid.bloom.respos;

import com.bonnysid.bloom.model.Role;
import com.bonnysid.bloom.model.enums.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(Roles name);
}
