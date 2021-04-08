package com.bonnysid.bloom.model.view;

import java.time.LocalDate;

public class DialogView {
    private Long id;
    private String username;
    private String photo;
    private MessageView lastMessage;

    public DialogView(Long id, String username, String photo, MessageView lastMessage) {
        this.id = id;
        this.username = username;
        this.photo = photo;
        this.lastMessage = lastMessage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MessageView getMessage() {
        return lastMessage;
    }

    public void setMessage(MessageView lastMessage) {
        this.lastMessage = lastMessage;
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

    @Override
    public String toString() {
        return "DialogView{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", photo='" + photo + '\'' +
                ", lastMessage=" + lastMessage +
                '}';
    }
}
