package com.example.elearning.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
@AllArgsConstructor
@Entity
public class UserAcces {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NotBlank(message = "username is mandatory")
    @Column(unique = true , nullable = false)
    String userName;
    @NotBlank(message = "password is mandatory")
    String encryptedpassword;
    @Column(nullable = true)
    boolean loggedIn =false;
    @Column(nullable = false, unique = true)
    String mail;
    @OneToOne(mappedBy = "accesUser")
    User user;

    public UserAcces(String userName, String encodedPassword,  String mail) {
        this.userName=userName;
        this.encryptedpassword=encodedPassword;
        this.loggedIn=false;
        this.mail=mail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEncryptedpassword() {
        return encryptedpassword;
    }

    public void setEncryptedpassword(String encryptedpassword) {
        this.encryptedpassword = encryptedpassword;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @JsonIgnore
    public User getUser() {
        return user;
    }
    @JsonSetter
    public void setUser(User user) {
        this.user = user;
    }
}
