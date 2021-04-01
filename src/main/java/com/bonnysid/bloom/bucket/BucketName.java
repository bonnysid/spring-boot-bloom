package com.bonnysid.bloom.bucket;

public enum BucketName {
    PROFILE_IMAGE("bloom-avatar");

    private final String name;

    BucketName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
