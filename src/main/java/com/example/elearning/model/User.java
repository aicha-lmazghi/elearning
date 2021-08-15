package com.example.elearning.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
@AllArgsConstructor

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String firstName;
    String lastName;
    String Username;
    String password;
    String mail;
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
}
