package com.example.elearning.service.impl;

import com.example.elearning.model.SubTopic;
import com.example.elearning.model.Topic;
import com.example.elearning.model.User;
import com.example.elearning.repository.RessourceRepository;
import com.example.elearning.repository.SubTopicRepository;
import com.example.elearning.repository.TopicRepository;
import com.example.elearning.repository.UserRepository;
import com.example.elearning.service.SubTopicService;
import com.example.elearning.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class SubTopicServiceImpl implements SubTopicService {
    @Autowired
    private SubTopicRepository subTopicRepository;
    @Autowired
    private TopicService topicService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private RessourceRepository ressourceRepository;

    @Override
    public ResponseEntity<Integer> addSubTopic(SubTopic subTopic) {
        String slug = topicService.generateSlug(subTopic.getName());
        subTopic.setSlug(slug);
        Topic subTopicRes = subTopicRepository.findBySlug(slug);
        if (subTopicRes != null) {
            return new ResponseEntity<>(-1, HttpStatus.FORBIDDEN);
        } else {
            Optional<User> optuser = userRepository.findById(subTopic.getCreatedBy().getId());
            Optional<Topic> opTopic = topicRepository.findById(subTopic.getTopic().getId());
            if (!optuser.isPresent()) {
                return new ResponseEntity<>(-2, HttpStatus.FORBIDDEN);
            } else {
                if (!opTopic.isPresent()) {
                    return new ResponseEntity<>(-3, HttpStatus.FORBIDDEN);
                } else {
                    User user = optuser.get();
                    Topic topic = opTopic.get();
                    subTopic.setCreatedBy(user);
                    subTopic.setTopic(topic);
                    subTopicRepository.save(subTopic);
                    return new ResponseEntity<>(1, HttpStatus.OK);

                }
            }
        }
    }

    @Override
    @Transactional
    public ResponseEntity<Integer> deleteSubTopic(Long id) {
        subTopicRepository.deleteById(id);
        return new ResponseEntity<>(1,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Integer> updateSubTopic(SubTopic subTopic) {
        subTopic.setSlug(topicService.generateSlug(subTopic.getName()));
        subTopicRepository.save(subTopic);
        return new ResponseEntity<>(1,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<SubTopic>> getAllSubTopics() {
        return new ResponseEntity<>(subTopicRepository.findAll(),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<SubTopic>> getAllSubTopicsOfUser(Long id) {
        return new ResponseEntity<>(subTopicRepository.findByCreatedById(id),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<SubTopic>> getAllSubTopicsOfTopic(Long id) {
        return new ResponseEntity<>(subTopicRepository.findByTopicId(id),HttpStatus.OK);
    }
}
