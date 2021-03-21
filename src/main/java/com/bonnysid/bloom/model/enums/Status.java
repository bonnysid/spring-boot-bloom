package com.bonnysid.bloom.model.enums;

public enum Status {
    ACTIVE("ACTIVE"),
    BANNED("BANNED");

    private String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
