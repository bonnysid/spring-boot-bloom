package com.bonnysid.bloom.model.view;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

public class MessageView {
    private Long id;
    private String text;
    private String fromUsername;

    public MessageView() {
    }

    public MessageView(Long id, String text, String fromUsername) {
        this.id = id;
        this.text = text;
        this.fromUsername = fromUsername;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFromUsername() {
        return fromUsername;
    }

    public void setFromUsername(String fromUsername) {
        this.fromUsername = fromUsername;
    }

    @Override
    public String toString() {
        return "MessageView{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", fromUsername='" + fromUsername + '\'' +
                '}';
    }
}
