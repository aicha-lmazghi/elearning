package com.example.elearning.service.impl;


import com.example.elearning.model.NiveauDifficulte;
import com.example.elearning.model.Ressource;
import com.example.elearning.repository.NiveauDiffRepository;
import com.example.elearning.repository.RessourceRepository;
import com.example.elearning.service.NiveauDiffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
public class NiveauDiffServiceImpl implements NiveauDiffService {
    @Autowired
    private NiveauDiffRepository niveauDiffRepository;
    @Autowired
    private RessourceRepository ressourceRepository;
    @Override
    public ResponseEntity<Integer> addNiveauDiff(NiveauDifficulte niveauDifficulte) {
        NiveauDifficulte niveauDifficulteRes = niveauDiffRepository.findByNiveau(niveauDifficulte.getNiveau());
        if(niveauDifficulteRes!= null){
            return new ResponseEntity<>(-1, HttpStatus.FORBIDDEN);
        }
        else{
            niveauDiffRepository.save(niveauDifficulte);
            return new ResponseEntity<>(1, HttpStatus.OK);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<Integer> deleteNiveauDiff(Long id) {
        niveauDiffRepository.deleteById(id);
        return new ResponseEntity<>(1,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Integer> updateNiveauDiff(NiveauDifficulte niveauDifficulte) {
        niveauDiffRepository.save(niveauDifficulte);
        return new ResponseEntity<>(1,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<NiveauDifficulte>> getAllNiveauDiff() {
        return new ResponseEntity<>(niveauDiffRepository.findAll(),HttpStatus.OK); }
}
