package com.example.elearning.service;

import com.example.elearning.model.Credentials;
import com.example.elearning.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface UserService {
    ResponseEntity<Integer> addUser(User user) throws Exception;
    ResponseEntity<User> findUserById(Long id);
    ResponseEntity<List<User>> findAllUsers();
    ResponseEntity<List<User>> findUserByFirstName(String firstName);
    ResponseEntity<List<User>> findUserByLastName(String lastName);
    ResponseEntity<User> findUserByUserName(String lastName);
    ResponseEntity<Integer> logUser(Credentials credentials) throws Exception ;
    ResponseEntity<Integer> logOutUser(Long id);
    ResponseEntity<List<User>> findUserByRoleName(String roleName);

}
