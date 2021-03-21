package com.bonnysid.bloom.model;

import com.bonnysid.bloom.model.enums.LinksTypes;

import javax.persistence.*;

@Entity
@Table(name = "links")
public class Link {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "title", nullable = false, columnDefinition = "varchar(50)")
    private LinksTypes title;

    @Column(name = "url", nullable = false, columnDefinition = "varchar(255)")
    private String url;

    public Link() {
    }

    public Link(LinksTypes title, String url) {
        this.title = title;
        this.url = url;
    }

    public Link(long id, LinksTypes title, String url) {
        this.id = id;
        this.title = title;
        this.url = url;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LinksTypes getTitle() {
        return title;
    }

    public void setTitle(LinksTypes title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String text) {
        this.url = text;
    }

    @Override
    public String toString() {
        return "Link{" +
                "title=" + title +
                ", url='" + url + '\'' +
                '}';
    }
}
