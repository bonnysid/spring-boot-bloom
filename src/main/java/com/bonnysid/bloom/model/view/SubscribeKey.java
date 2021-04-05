package com.bonnysid.bloom.model.view;

import java.io.Serializable;
import java.util.Objects;

public class SubscribeKey implements Serializable {
    private String email;
    private Long idFollowedUser;

    public SubscribeKey() {
    }

    public SubscribeKey(String email, Long idFollowedUser) {
        this.email = email;
        this.idFollowedUser = idFollowedUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubscribeKey that = (SubscribeKey) o;
        return Objects.equals(email, that.email) && Objects.equals(idFollowedUser, that.idFollowedUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, idFollowedUser);
    }
}
