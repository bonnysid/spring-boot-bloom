package com.bonnysid.bloom.model;

import java.time.LocalDate;
import java.util.List;

public class UserView extends UserViewForUserList{
    private LocalDate dateOfBirthday;
    private List<Post> posts;
    private List<Link> contacts;
    private Integer age;

    public UserView(User u) {
        super(u);
        this.posts = u.getPosts();
        this.age = u.getAge();
        this.dateOfBirthday = u.getDateOfBirthday();
        this.contacts = u.getContacts();
    }

    public UserView(User u, boolean followed) {
        super(u);
        this.posts = u.getPosts();
        this.age = u.getAge();
        this.dateOfBirthday = u.getDateOfBirthday();
        this.contacts = u.getContacts();
        this.setFollowed(followed);
    }

    public List<Link> getContacts() {
        return contacts;
    }

    public void setContacts(List<Link> contacts) {
        this.contacts = contacts;
    }

    public LocalDate getDateOfBirthday() {
        return dateOfBirthday;
    }

    public void setDateOfBirthday(LocalDate dateOfBirthday) {
        this.dateOfBirthday = dateOfBirthday;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "UserView{" +
                "dateOfBirthday=" + dateOfBirthday +
                ", posts=" + posts +
                ", contacts=" + contacts +
                ", age=" + age +
                '}';
    }
}
