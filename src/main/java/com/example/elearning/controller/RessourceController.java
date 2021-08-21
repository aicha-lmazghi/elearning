package com.example.elearning.controller;

import com.example.elearning.model.Ressource;
import com.example.elearning.service.RessourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Elearning/Ressource")
public class RessourceController {

    @PostMapping("/save")
    public ResponseEntity<List<Integer>> addRessource(@RequestBody Ressource ressource) {
        return ressourceService.addRessource(ressource);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Integer> deleteRessource(@PathVariable Long id) {
        return ressourceService.deleteRessource(id);
    }
    @PutMapping("/update")
    public ResponseEntity<Integer> updateRessource(@RequestBody Ressource ressource) {
        return ressourceService.updateRessource(ressource);
    }
    @GetMapping("/list")
    public ResponseEntity<List<Ressource>> getAllRessource() {
        return ressourceService.getAllRessource();
    }
    @GetMapping("/listByUser/{id}")
    public ResponseEntity<List<Ressource>> getAllRessourcesOfUser(@PathVariable Long id) {
        return ressourceService.getAllRessourceOfUser(id);
    }
    @GetMapping("/listBySubTopic/{id}")
    public ResponseEntity<List<Ressource>> getAllRessourceOfSubTopic(@PathVariable Long id) {
        return ressourceService.getAllRessourceOfSubTopic(id);
    }
    @GetMapping("/listByNiveauDiff/{id}")
    public ResponseEntity<List<Ressource>> getAllRessourceOfNiveauDiff(@PathVariable Long id) {
        return ressourceService.getAllRessourceOfNivDiff(id);
    }
    @Autowired
    private RessourceService ressourceService;

}
