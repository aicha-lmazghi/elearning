package com.example.elearning.repository;

import com.example.elearning.model.Ressource;
import com.example.elearning.model.SubTopic;
import com.example.elearning.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RessourceRepository extends JpaRepository<Ressource,Long> {
    Ressource findByName(String name);
    List<Ressource> findByCreatedById(Long id);
    List<Ressource> findBySubTopicId(Long id);
    List<Ressource> findByNiveauDifficulteId(Long id);
    int  deleteBySubTopicId(Long id);
    int deleteByNiveauDifficulteId(Long id);


}
