package com.bonnysid.bloom.model;

import javax.persistence.*;

import static javax.persistence.CascadeType.ALL;

@Entity
//@Table(name = "dialogs")
public class Dialog {
    @Id
    @SequenceGenerator(
            name = "dialog_sequence",
            sequenceName = "dialog_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dialog_sequence")
    private Long id;

    @Column(name = "id_from_user")
    private Long idFromUser;

    @Column(name = "id_to_user")
    private Long idToUser;

    @OneToOne(cascade = ALL, orphanRemoval = true)
    @JoinColumn(name = "id_last_message", insertable = false)
    private Message lastMessage;


    public Dialog() {
    }

    public Dialog(Long id, Long idFromUser, Long idToUser, Message lastMessage) {
        this.id = id;
        this.idFromUser = idFromUser;
        this.idToUser = idToUser;
        this.lastMessage = lastMessage;
    }

    public Dialog(Long idFromUser, Long idToUser) {
        this.idFromUser = idFromUser;
        this.idToUser = idToUser;
    }

    public Long getId() {
        return id;
    }

    public Message getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(Message lastMessage) {
        this.lastMessage = lastMessage;
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
                ", idLastMessage='" + lastMessage + '\'' +
                '}';
    }
}
