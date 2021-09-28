package com.example.elearning.repository;


import com.example.elearning.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicRepository extends JpaRepository<Topic,Long> {
         Topic findBySlug(String slug);
         List<Topic> findByCreatedById(Long id);
         int deleteByCreatedById(Long id);

}
