package com.example.elearning.service.impl;

import com.example.elearning.model.*;
import com.example.elearning.repository.NiveauDiffRepository;
import com.example.elearning.repository.RessourceRepository;
import com.example.elearning.repository.SubTopicRepository;
import com.example.elearning.repository.UserRepository;
import com.example.elearning.service.RessourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RessourceServiceImpl implements RessourceService {
    @Autowired
    private RessourceRepository ressourceRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SubTopicRepository subTopicRepository;
    @Autowired
    private NiveauDiffRepository niveauDiffRepository;
    @Override
    public ResponseEntity<List<Integer>> addRessource(Ressource ressource) {
            Optional<User> optuser = userRepository.findById(ressource.getCreatedBy().getId());
            Optional<SubTopic> opSubTopic = subTopicRepository.findById(ressource.getSubTopic().getId());
            Optional<NiveauDifficulte> opNiveDiff = niveauDiffRepository.findById(ressource.getNiveauDifficulte().getId());
            List<Integer> res = new ArrayList<>();
            if (!optuser.isPresent()) {
                res.add(-1);
            }
            if(!opSubTopic.isPresent()) {
                res.add(-2);
            }
            if (!opNiveDiff.isPresent()) {
                res.add(-3);
            }
            if(res.size()== 0){
                User user = optuser.get();
                SubTopic subTopic = opSubTopic.get();
                NiveauDifficulte niveauDifficulte = opNiveDiff.get();
                ressource.setCreatedBy(user);
                ressource.setSubTopic(subTopic);
                ressource.setNiveauDifficulte(niveauDifficulte);
                ressourceRepository.save(ressource);
                res.add(1);
            }
           return new ResponseEntity<>(res, HttpStatus.OK); }

    @Override
    @Transactional
    public ResponseEntity<Integer> deleteRessource(Long id) {
        ressourceRepository.deleteById(id);
        return new ResponseEntity<>(1,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Integer> updateRessource(Ressource ressource) {
        ressourceRepository.save(ressource);
        return new ResponseEntity<>(1,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Ressource>> getAllRessource() {
        return new ResponseEntity<>(ressourceRepository.findAll(),HttpStatus.OK);

    }

    @Override
    public ResponseEntity<List<Ressource>> getAllRessourceOfUser(Long id) {
        return new ResponseEntity<>(ressourceRepository.findByCreatedById(id),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Ressource>> getAllRessourceOfSubTopic(Long id) {
        return new ResponseEntity<>(ressourceRepository.findBySubTopicId(id),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Ressource>> getAllRessourceOfNivDiff(Long id) {
        return new ResponseEntity<>(ressourceRepository.findByNiveauDifficulteId(id),HttpStatus.OK);
    }
}
