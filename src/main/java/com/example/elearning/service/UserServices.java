package com.example.elearning.service;


import com.example.elearning.model.AccesUserDto;
import com.example.elearning.model.User;
import com.example.elearning.model.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserServices extends UserDetailsService {
    ResponseEntity<Integer> addUser(UserDto userdto) throws Exception;
    ResponseEntity<User> findUserById(Long id);
    ResponseEntity<User> findUserByUserId(String id);
    ResponseEntity<List<User>> findAllUsers();
    ResponseEntity<List<User>> findUserByFirstName(String firstName);
    ResponseEntity<List<User>> findUserByLastName(String lastName);
    ResponseEntity<UserDto> findUserByUserName(String lastName);
    ResponseEntity<Integer> logUser(AccesUserDto accesUserdto) throws Exception ;
    ResponseEntity<Integer> logOutUser(Long id);
    ResponseEntity<List<User>> findUserByRoleName(String roleName);
    ResponseEntity<Integer> deleteUserById(String idUser);
    ResponseEntity<Integer> updateUser(UserDto userdto);

}
