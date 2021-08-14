package com.example.elearning.service.impl;

import com.example.elearning.model.User;
import com.example.elearning.repository.UserRepository;
import com.example.elearning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Override
    public ResponseEntity<String> addUser(User user) {
        User u=userRepository.save(user);
        if(u!=null){
            return new ResponseEntity<>("succes", HttpStatus.ACCEPTED);
        }else{
            return new ResponseEntity<>("Fail", HttpStatus.BAD_REQUEST);
        }
    }
}
