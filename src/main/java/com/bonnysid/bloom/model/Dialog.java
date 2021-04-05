package com.bonnysid.bloom.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "dialogs")
public class Dialog {
    @Id
    private Long id;

    @Column(name = "id_from_user")
    private Long idFromUser;

    @Column(name = "id_to_user")
    private Long idToUser;

    @Column(name = "last_message")
    private String lastMessage;

    @Column(name = "last_message_date")
    private LocalDate lastMessageDate;

    public Dialog() {
    }

    public Dialog(Long id, Long idFromUser, Long idToUser, String lastMessage, LocalDate lastMessageDate) {
        this.id = id;
        this.idFromUser = idFromUser;
        this.idToUser = idToUser;
        this.lastMessage = lastMessage;
        this.lastMessageDate = lastMessageDate;
    }

    public Long getId() {
        return id;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public LocalDate getLastMessageDate() {
        return lastMessageDate;
    }

    public void setLastMessageDate(LocalDate lastMessageDate) {
        this.lastMessageDate = lastMessageDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdFromUser() {
        return idFromUser;
    }

    public void setIdFromUser(Long idFromUser) {
        this.idFromUser = idFromUser;
    }

    public Long getIdToUser() {
        return idToUser;
    }

    public void setIdToUser(Long idToUser) {
        this.idToUser = idToUser;
    }

    @Override
    public String toString() {
        return "Dialog{" +
                "id=" + id +
                ", idFromUser=" + idFromUser +
                ", idToUser=" + idToUser +
                ", lastMessage='" + lastMessage + '\'' +
                ", lastMessageDate=" + lastMessageDate +
                '}';
    }
}
