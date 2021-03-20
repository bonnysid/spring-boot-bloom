package com.bonnysid.bloom.User;

import javax.persistence.*;
import javax.sql.DataSource;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.Period;

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
    private long id;
    private String username;
    private String email;
    private LocalDate dateOfBirthday;
    @Transient
    private int age;

    public User() {
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
