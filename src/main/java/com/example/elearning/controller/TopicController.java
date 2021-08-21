package com.example.elearning.controller;


import com.example.elearning.model.Topic;
import com.example.elearning.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("Elearning/Topic")
public class TopicController {

    @Autowired
    private TopicService topicService;
    @PostMapping("/save")
    public ResponseEntity<Integer> addTopic(@RequestBody Topic topic) {
        return topicService.addTopic(topic);
    }
    @GetMapping("/list")
    public ResponseEntity<List<Topic>> getAllTopics() {
        return topicService.getAllTopics();
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Integer> deleteTopic(@PathVariable  Long id) {
        return topicService.deleteTopic(id);
    }
    @PutMapping("/update")
    public ResponseEntity<Integer> updateTopic(@RequestBody Topic topic) {
        return topicService.updateTopic(topic);
    }
    @GetMapping("/list/{id}")
    public ResponseEntity<List<Topic>> getAllTopicsOfUser(@PathVariable Long id) {
        return topicService.getAllTopicsOfUser(id);
    }

}
