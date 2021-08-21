package com.example.elearning.service;

import com.example.elearning.model.SubTopic;
import com.example.elearning.model.Topic;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SubTopicService {
    ResponseEntity<Integer> addSubTopic(SubTopic subTopic);
    ResponseEntity<Integer> deleteSubTopic(Long id);
    ResponseEntity<Integer> updateSubTopic(SubTopic subTopic);
    ResponseEntity<List<SubTopic>> getAllSubTopics();
    ResponseEntity<List<SubTopic>> getAllSubTopicsOfUser(Long id);
    ResponseEntity<List<SubTopic>> getAllSubTopicsOfTopic(Long id);

}
