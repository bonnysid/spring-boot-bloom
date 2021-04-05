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

    @Column(name = "id_last_message")
    private String idLastMessage;


    public Dialog() {
    }

    public Dialog(Long id, Long idFromUser, Long idToUser, String idLastMessage) {
        this.id = id;
        this.idFromUser = idFromUser;
        this.idToUser = idToUser;
        this.idLastMessage = idLastMessage;
    }

    public Dialog(Long idFromUser, Long idToUser) {
        this.idFromUser = idFromUser;
        this.idToUser = idToUser;
    }

    public Long getId() {
        return id;
    }

    public String getIdLastMessage() {
        return idLastMessage;
    }

    public void setIdLastMessage(String idLastMessage) {
        this.idLastMessage = idLastMessage;
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
                ", idLastMessage='" + idLastMessage + '\'' +
                '}';
    }
}
