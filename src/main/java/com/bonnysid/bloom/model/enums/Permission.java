package com.bonnysid.bloom.model.enums;

public enum Permission {
    USER_READ("user:read"),
    USER_DELETE("user:delete"),
    USER_UPDATE("user:update"),
    USER_WRITE("user:write");

    private final String permission;

    public String getPermission() {
        return permission;
    }

    Permission(String permission) {
        this.permission = permission;
    }
}
