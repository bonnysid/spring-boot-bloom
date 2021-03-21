package com.bonnysid.bloom.model;

import com.bonnysid.bloom.model.enums.Status;

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
        this.photo = u.getPhoto();
        this.followed = false;
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

    public String getPhoto() {
        return photo;
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
}
