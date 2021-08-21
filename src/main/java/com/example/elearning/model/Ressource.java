package com.example.elearning.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


import javax.persistence.*;


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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @JsonIgnore
    public User getCreatedBy() {
        return createdBy;
    }
    @JsonSetter
    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }
    @JsonIgnore
    public SubTopic getSubTopic() {
        return subTopic;
    }
    @JsonSetter
    public void setSubTopic(SubTopic subTopic) {
        this.subTopic = subTopic;
    }
    @JsonIgnore
    public NiveauDifficulte getNiveauDifficulte() {
        return niveauDifficulte;
    }
    @JsonSetter
    public void setNiveauDifficulte(NiveauDifficulte niveauDifficulte) {
        this.niveauDifficulte = niveauDifficulte;
    }
}
