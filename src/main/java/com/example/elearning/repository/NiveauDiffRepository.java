package com.example.elearning.repository;

import com.example.elearning.model.NiveauDifficulte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NiveauDiffRepository  extends JpaRepository<NiveauDifficulte, Long> {
    NiveauDifficulte findByNiveau(String niveau);
}
