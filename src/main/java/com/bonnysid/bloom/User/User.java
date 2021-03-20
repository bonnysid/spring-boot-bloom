package com.bonnysid.bloom.User;

import com.bonnysid.bloom.Post.Post;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.sql.DataSource;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.CascadeType.REFRESH;
import static javax.persistence.FetchType.EAGER;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    @Column(name = "id", updatable = false)
    private long id;
    @Column(name = "username", nullable = false, unique = true, columnDefinition = "varchar(50)")
    private String username;
    @Column(name = "email", nullable = false, unique = true, columnDefinition = "varchar(50)")
    private String email;
    @Column(name = "status", columnDefinition = "varchar(50)")
    private String status;
    private String photo;
    @Column(name = "date_of_birthday")
    private LocalDate dateOfBirthday;
    @OneToMany(cascade = ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id", insertable = false)
    private List<Post> posts;
    @Transient
    private int age;

    public User() {
    }
    public String getStatus() {
        return status;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public User(String username, String email, String status, LocalDate dateOfBirthday) {
        this.username = username;
        this.email = email;
        this.status = status;
        this.dateOfBirthday = dateOfBirthday;
    }

    public void setStatus(String status) {
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

    public int getAge() {
        return Period.between(dateOfBirthday, LocalDate.now()).getYears();
    }

    public void setAge(int age) {
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
