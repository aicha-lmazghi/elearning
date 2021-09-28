package com.example.elearning.controller;


import com.example.elearning.model.AccesUserDto;
import com.example.elearning.model.User;
import com.example.elearning.model.UserDto;
import com.example.elearning.service.UserServices;
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
    UserServices userService;
    @SneakyThrows
    @PostMapping("/registerUser")
    public ResponseEntity<Integer> addUser(@Valid @RequestBody UserDto userdto) {

        return userService.addUser(userdto);
    }
    @PutMapping("/updateUser/{id}")
    public ResponseEntity<Integer> updateUser(@Valid @RequestBody UserDto userdto,@PathVariable String id) {

        return userService.updateUser(userdto,id);
    }
    @SneakyThrows
    @PostMapping("/logUser")
    public ResponseEntity<Integer> logUser(@Valid @RequestBody AccesUserDto accesUserdto) {
        return userService.logUser(accesUserdto);
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
    @GetMapping("/userid/{userid}")
    public ResponseEntity<User> getByIdUser(@PathVariable("userid") String userid){
        return userService.findUserByUserId(userid);
    }
    @DeleteMapping("/userid/{userid}")
    public ResponseEntity<Integer> deleteById(@PathVariable("userid") String id){
        return userService.deleteUserById(id);
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
    public ResponseEntity<UserDto> getByUsername(@PathVariable("username") String username){
        return userService.findUserByUserName(username);
    }
    @GetMapping("/role/{rolename}")
    public ResponseEntity<List<User>> getByRole(@PathVariable("rolename") String roleName){
        return userService.findUserByRoleName(roleName);
    }

}
