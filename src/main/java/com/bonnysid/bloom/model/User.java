package com.bonnysid.bloom.model;

import com.bonnysid.bloom.model.enums.Status;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

import static javax.persistence.CascadeType.ALL;

@Entity(name = "users")
//@Table(name = "Users")
public class User {
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    @Column(name = "id", updatable = false)
    private long id;

    @Column(name = "username", nullable = false, unique = true, columnDefinition = "varchar(50)")
    private String username;

    @Column(name = "email", nullable = false, unique = true, columnDefinition = "varchar(255)")
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", columnDefinition = "varchar(20)", nullable = false)
    private Status status;

    @Column(name = "profile_status", columnDefinition = "varchar(20)")
    private String profileStatus;

    private String photo;

    @Column(name = "date_of_birthday")
    private LocalDate dateOfBirthday;

    @OneToMany(cascade = ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id", insertable = false)
    private List<Post> posts;

    @OneToMany(cascade = ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id", insertable = false)
    private List<Link> contacts;

    @Transient
    private Integer age;

    @ManyToMany(fetch = FetchType.LAZY, cascade = ALL)
    @JoinTable(name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Role> roles = new HashSet<>();

    public User() {
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.status = Status.ACTIVE;
//        this.role = new HashSet<>(new Role(""))
    }

    public User(String username, String email, String profileStatus, LocalDate dateOfBirthday) {
        this.username = username;
        this.email = email;
        this.profileStatus = profileStatus;
        this.dateOfBirthday = dateOfBirthday;
    }

    public User(String username, String email, Status status, LocalDate dateOfBirthday) {
        this.username = username;
        this.email = email;
        this.status = status;
        this.dateOfBirthday = dateOfBirthday;

    }

    public List<Post> getPosts() {
        return posts;
    }

    public List<Link> getContacts() {
        return contacts;
    }

    public void setContacts(List<Link> contacts) {
        this.contacts = contacts;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> role) {
        this.roles = role;
    }

    public Status getStatus() {
        return status;
    }

    public Optional<String> getPhoto() {
        return Optional.ofNullable(photo);
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getProfileStatus() {
        return profileStatus;
    }

    public void setProfileStatus(String profileStatus) {
        this.profileStatus = profileStatus;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

//    public List<Post> getPosts() {
//        return posts;
//    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public User(String username, String email, LocalDate dateOfBirthday) {
        this.username = username;
        this.email = email;
        this.dateOfBirthday = dateOfBirthday;

    }

    public User(long id, String username, String email, LocalDate dateOfBirthday) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.dateOfBirthday = dateOfBirthday;
    }

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateOfBirthday() {
        return dateOfBirthday;
    }

    public void setDateOfBirthday(LocalDate dateOfBirthday) {
        this.dateOfBirthday = dateOfBirthday;
    }

    public Integer getAge() {
        return dateOfBirthday != null ? Period.between(dateOfBirthday, LocalDate.now()).getYears() : null;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", dateOfBirthday=" + dateOfBirthday +
                ", age=" + age +
                '}';
    }
}
