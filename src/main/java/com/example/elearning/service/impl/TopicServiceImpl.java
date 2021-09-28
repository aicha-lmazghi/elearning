package com.example.elearning.service.impl;

import com.example.elearning.model.Topic;
import com.example.elearning.model.User;
import com.example.elearning.repository.RessourceRepository;
import com.example.elearning.repository.SubTopicRepository;
import com.example.elearning.repository.TopicRepository;
import com.example.elearning.repository.UserRepository;
import com.example.elearning.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class TopicServiceImpl implements TopicService {
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SubTopicRepository subTopicRepository;
    @Autowired
    private RessourceRepository ressourceRepository;
    @Override
    public ResponseEntity<Integer> addTopic(Topic topic) {
        String slug = generateSlug(topic.getName());
        topic.setSlug(slug);
        Topic topicRes = topicRepository.findBySlug(slug);
        if(topicRes != null){
            return new ResponseEntity<>(-1, HttpStatus.FORBIDDEN);
        }
        else{
            Optional<User> optuser = userRepository.findById(topic.getCreatedBy().getId());
            if(!optuser.isPresent()){
                return new ResponseEntity<>(-2, HttpStatus.FORBIDDEN);
            }
            else{
                User user =optuser.get();
                topic.setCreatedBy(user);
                topicRepository.save(topic);
                return new ResponseEntity<>(1, HttpStatus.OK);

            }
        }

    }

    @Override
    @Transactional
    public ResponseEntity<Integer> deleteTopic(Long id) {
        topicRepository.deleteById(id);
        return new ResponseEntity<>(1,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Integer> updateTopic(Topic topic) {
        topic.setSlug(generateSlug(topic.getName()));
        topicRepository.save(topic);
        return new ResponseEntity<>(1,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Topic>> getAllTopics() {
        List<Topic> list = topicRepository.findAll();
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Topic>> getAllTopicsOfUser(Long id) {
        List<Topic> list = topicRepository.findByCreatedById(id);
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @Override
    public String generateSlug(String name) {
        return  name.toLowerCase().replace(" ","-");
    }



}
