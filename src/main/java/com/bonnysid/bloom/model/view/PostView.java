package com.bonnysid.bloom.model.view;

import com.amazonaws.services.workmail.model.User;
import com.bonnysid.bloom.model.Post;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import java.time.LocalDateTime;

public class PostView {
    private long id;

    private String title;

    private String text;

    private LocalDateTime date;

    private UserListView user;

    private int likeCount;

    public PostView() {
    }

    public PostView(Post post, UserListView user) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.text = post.getText();
        this.date = post.getDate();
        this.user = user;
        this.likeCount = post.getLikeCount();
    }

    public PostView(long id, String title, String text, LocalDateTime date, UserListView user, int likeCount) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.date = date;
        this.user = user;
        this.likeCount = likeCount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public UserListView getUser() {
        return user;
    }

    public void setUser(UserListView user) {
        this.user = user;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    @Override
    public String toString() {
        return "PostView{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", date='" + date + '\'' +
                ", user=" + user +
                ", likeCount=" + likeCount +
                '}';
    }
}
