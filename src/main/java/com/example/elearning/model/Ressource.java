package com.example.elearning.model;

import lombok.*;
import lombok.experimental.FieldDefaults;


import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
@AllArgsConstructor
@Entity
public class Ressource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String image;
    String description;
    @ManyToOne
    User createdBy;
    @ManyToOne
    SubTopic subTopic;
    @ManyToOne
    NiveauDifficulte niveauDifficulte;
}
