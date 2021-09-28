package com.example.elearning.service;

import com.example.elearning.model.Ressource;
import com.example.elearning.model.SubTopic;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RessourceService {
    ResponseEntity<List<Integer>> addRessource(Ressource ressource);
    ResponseEntity<Integer> deleteRessource(Long id);
    ResponseEntity<Integer> updateRessource(Ressource ressource);
    ResponseEntity<List<Ressource>> getAllRessource();
    ResponseEntity<List<Ressource>> getAllRessourceOfUser(Long id);
    ResponseEntity<List<Ressource>> getAllRessourceOfSubTopic(Long id);
    ResponseEntity<List<Ressource>> getAllRessourceOfNivDiff(Long id);

}
