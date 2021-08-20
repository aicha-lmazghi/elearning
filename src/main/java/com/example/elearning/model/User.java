package com.example.elearning.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;



@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
@AllArgsConstructor

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NotBlank(message = "first Name is mandatory")
    String firstName;
    @NotBlank(message = "last Name is mandatory")
    String lastName;
    @NotBlank(message = "username is mandatory")
    @Column(unique = true , nullable = false)
    String userName;
    @NotBlank(message = "password is mandatory")
    String password;
    boolean loggedIn;
    @NotBlank(message = "email is mandatory")
    @Column(nullable = false, unique = true)
    String mail;
    @NotBlank(message = "number is mandatory")
    String fullNumber;
    String image;
    String dateNaiss;
    @ManyToOne
    Role role;
    @OneToMany(mappedBy = "createdBy")
    List<Topic> topics;
    @OneToMany(mappedBy = "createdBy")
    List<SubTopic> subtopics;
    @OneToMany(mappedBy = "createdBy")
    List<Ressource> ressources;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getFullNumber() {
        return fullNumber;
    }

    public void setFullNumber(String fullNumber) {
        this.fullNumber = fullNumber;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDateNaiss() {
        return dateNaiss;
    }

    public void setDateNaiss(String dateNaiss) {
        this.dateNaiss = dateNaiss;
    }
    @JsonIgnore
    public Role getRole() {
        return role;
    }
    @JsonSetter
    public void setRole(Role role) {
        this.role = role;
    }
    @JsonIgnore
    public List<Topic> getTopics() {
        return topics;
    }
    @JsonSetter
    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }
    @JsonIgnore
    public List<SubTopic> getSubtopics() {
        return subtopics;
    }
    @JsonSetter
    public void setSubtopics(List<SubTopic> subtopics) {
        this.subtopics = subtopics;
    }
    @JsonIgnore
    public List<Ressource> getRessources() {
        return ressources;
    }
    @JsonSetter
    public void setRessources(List<Ressource> ressources) {
        this.ressources = ressources;
    }
}
