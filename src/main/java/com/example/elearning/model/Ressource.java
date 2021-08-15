package com.example.elearning.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
@AllArgsConstructor
@Document(collection = "Ressources")
public class Ressource {
    @Id
    String id;
    String name;
    String image;
    String description;
    String createdBy;
    String subTopic;
    NiveauDifficulte niveauDifficulte;
}
