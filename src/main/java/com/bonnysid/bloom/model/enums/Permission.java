package com.bonnysid.bloom.model.enums;

public enum Permission {
    USER_READ("user:read"),
    USER_WRITE("user:write");

    private String permission;

    public String getPermission() {
        return permission;
    }

    Permission(String permission) {
        this.permission = permission;
    }
}
