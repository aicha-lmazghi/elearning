package com.example.elearning.controller;


import com.example.elearning.model.NiveauDifficulte;
import com.example.elearning.service.NiveauDiffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Elearning/NiveauDifficulte")
public class NiveauDiffController {

    @PostMapping("/save")
    public ResponseEntity<Integer> addNiveauDiff(@RequestBody NiveauDifficulte niveauDifficulte) {
        return niveauDiffService.addNiveauDiff(niveauDifficulte);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Integer> deleteNiveauDiff(@PathVariable Long id) {
        return niveauDiffService.deleteNiveauDiff(id);
    }
    @PutMapping("/update")
    public ResponseEntity<Integer> updateNiveauDiff(@RequestBody NiveauDifficulte niveauDifficulte) {
        return niveauDiffService.updateNiveauDiff(niveauDifficulte);
    }
    @GetMapping("/list")
    public ResponseEntity<List<NiveauDifficulte>> getAllNiveauDiff() {
        return niveauDiffService.getAllNiveauDiff();
    }

    @Autowired
    private NiveauDiffService niveauDiffService;

}
