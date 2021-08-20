package com.example.elearning.controller;

import com.example.elearning.model.Credentials;
import com.example.elearning.model.User;
import com.example.elearning.service.UserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;
    @SneakyThrows
    @PostMapping("/registerUser")
    public ResponseEntity<Integer> addUser(@Valid @RequestBody User user) {
        return userService.addUser(user);
    }
    @SneakyThrows
    @PostMapping("/login")
    public ResponseEntity<Integer> logUser(@Valid @RequestBody Credentials credentials) {
        return userService.logUser(credentials);
    }
    @GetMapping("/logout/{id}")
    public ResponseEntity<Integer> logUserOut(@PathVariable Long id) {
        return userService.logOutUser(id);
    }
    @GetMapping("/")
    public ResponseEntity<List<User>> getAll(){
        return userService.findAllUsers();
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<User> getById(@PathVariable("id") Long id){
        return userService.findUserById(id);
    }
    @GetMapping("/firstname/{firstname}")
    public ResponseEntity<List<User>> getByFirstname(@PathVariable("firstname") String firstname){
        return userService.findUserByFirstName(firstname);
    }
    @GetMapping("/lastname/{lastname}")
    public ResponseEntity<List<User>> getByLastname(@PathVariable("lastname") String lastname){
        return userService.findUserByLastName(lastname);
    }
    @GetMapping("/username/{username}")
    public ResponseEntity<User> getByUsername(@PathVariable("username") String username){
        return userService.findUserByUserName(username);
    }
    @GetMapping("/role/{rolename}")
    public ResponseEntity<List<User>> getByRole(@PathVariable("rolename") String roleName){
        return userService.findUserByRoleName(roleName);
    }

}
