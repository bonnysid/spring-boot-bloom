package com.bonnysid.bloom.model.enums;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum RolesWithPermissions {
    USER(Set.of(Permission.USER_READ, Permission.USER_WRITE)),
    MODERATOR(Set.of(Permission.USER_WRITE, Permission.USER_READ, Permission.USER_UPDATE)),
    ADMIN(Set.of(Permission.USER_WRITE, Permission.USER_READ, Permission.USER_UPDATE, Permission.USER_DELETE));

    public Set<Permission> getPermission() {
        return permission;
    }

    private final Set<Permission> permission;

    RolesWithPermissions(Set<Permission> permission) {
        this.permission = permission;
    }

    public Set<SimpleGrantedAuthority> getAuthorities(){
        return getPermission().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}
