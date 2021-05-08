package com.bonnysid.bloom.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
//@Table(name = "message")
public class Message {
    @Id
    @SequenceGenerator(
            name = "mes_sequence",
            sequenceName = "mes_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mes_sequence")
    private Long id;

    @Column(name = "text")
    private String text;

    @Column(name = "id_dialog")
    private Long dialogId;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "id_from_user")
    private Long idFromUser;

    public Message() {
    }

    public Message(Long id, String text, Long dialogId, Long idFromUser, LocalDate date) {
        this.id = id;
        this.text = text;
        this.dialogId = dialogId;
        this.date = date;
        this.idFromUser = idFromUser;
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

    public Long getIdFromUser() {
        return idFromUser;
    }

    public void setIdFromUser(Long idFromUser) {
        this.idFromUser = idFromUser;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getDialogId() {
        return dialogId;
    }

    public void setDialogId(Long dialogId) {
        this.dialogId = dialogId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Message(String text, Long dialogId, LocalDate date) {
        this.text = text;
        this.dialogId = dialogId;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", dialogId=" + dialogId +
                ", date=" + date +
                '}';
    }
}
