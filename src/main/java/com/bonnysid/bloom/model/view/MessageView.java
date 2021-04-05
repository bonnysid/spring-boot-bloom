package com.bonnysid.bloom.model.view;

import java.time.LocalDate;

public class MessageView {
    private Long id;
    private String text;
    private String fromUsername;
    private LocalDate date;

    public MessageView() {
    }

    public MessageView(Long id, String text, String fromUsername, LocalDate date) {
        this.id = id;
        this.text = text;
        this.fromUsername = fromUsername;
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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
                ", date=" + date +
                '}';
    }
}
