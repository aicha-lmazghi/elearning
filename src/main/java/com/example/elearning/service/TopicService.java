package com.example.elearning.service;


import com.example.elearning.model.Topic;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;


public interface TopicService {
    ResponseEntity<Integer> addTopic(Topic topic);
    ResponseEntity<Integer> deleteTopic(Long id);
    ResponseEntity<Integer> updateTopic(Topic topic);
    ResponseEntity<List<Topic>> getAllTopics();
    ResponseEntity<List<Topic>> getAllTopicsOfUser(Long id);
    String generateSlug(String name);


}
