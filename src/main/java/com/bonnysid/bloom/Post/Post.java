package com.bonnysid.bloom.Post;

import javax.persistence.*;

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @SequenceGenerator(
            name = "post_sequence",
            sequenceName = "post_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_sequence")
    private long id;
    private String title;
    private String text;


    public Post() {
    }

    private long authorId;

    public Post(String title, String text) {
        this.title = title;
        this.text = text;
    }

    public Post(String title, String text, long authorId) {
        this.title = title;
        this.text = text;
        this.authorId = authorId;
    }

    public Post(long id, String title, String text, long authorId) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.authorId = authorId;
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

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }
}
