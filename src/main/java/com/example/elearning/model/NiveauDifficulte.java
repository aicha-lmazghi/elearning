package com.example.elearning.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
@AllArgsConstructor
@Entity
public class NiveauDifficulte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String niveau;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "niveauDifficulte", orphanRemoval = true)
    List<Ressource> ressources;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
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
