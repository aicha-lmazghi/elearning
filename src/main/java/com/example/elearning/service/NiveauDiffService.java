package com.example.elearning.service;

import com.example.elearning.model.NiveauDifficulte;
import com.example.elearning.model.Topic;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


public interface NiveauDiffService {
    ResponseEntity<Integer> addNiveauDiff(NiveauDifficulte niveauDifficulte);
    ResponseEntity<Integer> deleteNiveauDiff(Long id);
    ResponseEntity<Integer> updateNiveauDiff(NiveauDifficulte niveauDifficulte);
    ResponseEntity<List<NiveauDifficulte>> getAllNiveauDiff();

}
