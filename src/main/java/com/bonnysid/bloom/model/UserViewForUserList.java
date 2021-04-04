package com.bonnysid.bloom.model;

import com.bonnysid.bloom.model.enums.Status;

import java.util.Objects;
import java.util.Optional;

public class UserViewForUserList {
    private long id;
    private String username;
    private String email;
    private Status status;
    private String photo;
    private boolean followed;

    public UserViewForUserList(User u) {
        this.id = u.getId();
        this.username = u.getUsername();
        this.email = u.getEmail();
        this.status = u.getStatus();
        this.photo = u.getPhoto().orElse(null);
        this.followed = false;
    }

    public UserViewForUserList(User u, boolean followed) {
        this.id = u.getId();
        this.username = u.getUsername();
        this.email = u.getEmail();
        this.status = u.getStatus();
        this.photo = u.getPhoto().orElse(null);
        this.followed = followed;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Optional<String> getPhoto() {
        return Optional.ofNullable(photo);
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public boolean isFollowed() {
        return followed;
    }

    public void setFollowed(boolean followed) {
        this.followed = followed;
    }

    @Override
    public String toString() {
        return "UserModelForUserList{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", status=" + status +
                ", photo='" + photo + '\'' +
                ", followed=" + followed +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserViewForUserList that = (UserViewForUserList) o;
        return id == that.id && followed == that.followed && Objects.equals(username, that.username) && Objects.equals(email, that.email) && status == that.status && Objects.equals(photo, that.photo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email, status, photo, followed);
    }
}
