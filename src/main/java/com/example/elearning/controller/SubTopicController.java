package com.example.elearning.controller;

import com.example.elearning.model.SubTopic;
import com.example.elearning.service.SubTopicService;
import com.example.elearning.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Elearning/SubTopic")
public class SubTopicController {
    @PostMapping("/save")
    public ResponseEntity<Integer> addSubTopic(@RequestBody SubTopic subTopic) {
        return subTopicService.addSubTopic(subTopic);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Integer> deleteSubTopic(@PathVariable Long id) {
        return subTopicService.deleteSubTopic(id);
    }
    @PutMapping("/update")
    public ResponseEntity<Integer> updateSubTopic(@RequestBody SubTopic subTopic) {
        return subTopicService.updateSubTopic(subTopic);
    }
    @GetMapping("/list")
    public ResponseEntity<List<SubTopic>> getAllSubTopics() {
        return subTopicService.getAllSubTopics();
    }
    @GetMapping("/listOfSubTopicByUser/{id}")
    public ResponseEntity<List<SubTopic>> getAllSubTopicsOfUser(@PathVariable Long id) {
        return subTopicService.getAllSubTopicsOfUser(id);
    }
    @GetMapping("/listOfSubTopicByTopic/{id}")
    public ResponseEntity<List<SubTopic>> getAllSubTopicsOfTopic(@PathVariable Long id) {
        return subTopicService.getAllSubTopicsOfTopic(id);
    }

    @Autowired
    private SubTopicService subTopicService;

}
