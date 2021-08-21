package com.example.elearning.service;

import com.example.elearning.model.Role;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    ResponseEntity<Role> getById(Long id);
    ResponseEntity<List<Role>> getAll();
    ResponseEntity<Integer> addRole(Role role);
}
