package com.example.elearning.service;

import com.example.elearning.model.User;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<String> addUser(User user);
}
