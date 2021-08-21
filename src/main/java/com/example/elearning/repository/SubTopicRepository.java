package com.example.elearning.repository;


import com.example.elearning.model.SubTopic;
import com.example.elearning.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubTopicRepository extends JpaRepository<SubTopic,Long>{
    Topic findBySlug(String slug);
    List<SubTopic> findByCreatedById(Long id);
    List<SubTopic> findByTopicId(Long id);
    int deleteByCreatedById(Long id);
    int deleteByTopicId(Long id);

}
