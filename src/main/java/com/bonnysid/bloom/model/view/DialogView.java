package com.bonnysid.bloom.model.view;

import java.time.LocalDate;

public class DialogView {
    private Long id;
    private String username;
    private String photo;
    private LocalDate date;

    public DialogView(Long id, String username, String photo, LocalDate date) {
        this.id = id;
        this.username = username;
        this.photo = photo;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "DialogView{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", photo='" + photo + '\'' +
                ", date=" + date +
                '}';
    }
}
