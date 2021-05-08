package com.bonnysid.bloom.model.enums;

public enum Roles {
    MODERATOR("MODERATOR"),
    ADMIN("ADMIN"),
    USER("USER");

    private String type;

    Roles(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
