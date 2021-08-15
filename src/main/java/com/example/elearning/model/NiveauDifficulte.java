package com.example.elearning.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class NiveauDifficulte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String niveau;
    @OneToMany(mappedBy = "niveauDifficulte")
    List<Ressource> ressources;

}
