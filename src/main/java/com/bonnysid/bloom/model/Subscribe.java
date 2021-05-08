package com.bonnysid.bloom.model;

import com.bonnysid.bloom.model.view.SubscribeKey;

import javax.persistence.*;

@Entity
//@Table(name = "subscribes")
@IdClass(SubscribeKey.class)
public class Subscribe {
    @Id
    private String email;
    @Id
    private Long idFollowedUser;

    public Subscribe() {
    }

    public Subscribe(String email, Long idFollowedUser) {
        this.email = email;
        this.idFollowedUser = idFollowedUser;
    }

    public String getLoggedUser() {
        return email;
    }

    public void setLoggedUser(String loggedUser) {
        this.email = loggedUser;
    }

    public long getIdFollowedUser() {
        return idFollowedUser;
    }

    public void setIidFollowedUser(long idFollowedUser) {
        this.idFollowedUser = idFollowedUser;
    }

}
