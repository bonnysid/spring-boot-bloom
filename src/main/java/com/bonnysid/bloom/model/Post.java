package com.bonnysid.bloom.model;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
//@Table(name = "posts")
public class Post {
    @Id
    @SequenceGenerator(name = "post_seq", sequenceName = "post_seq", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "post_seq")
    private long id;

    @Column(name = "title", nullable = false, columnDefinition = "varchar(50)")
    private String title;

    @Column(name = "text", nullable = false, columnDefinition = "TEXT")
    private String text;

    @Column(name = "date", nullable = false, columnDefinition = "date")
    private String date;

    public Post(String title, String text, String date) {
        this.title = title;
        this.text = text;
        this.date = date;
    }

    public Post() {
    }

    public long getId() {
        return id;
    }

    public Post(String title, String text) {
        this.title = title;
        this.text = text;
    }

    public Post(long id, String title, String text) {
        this.id = id;
        this.title = title;
        this.text = text;
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

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
