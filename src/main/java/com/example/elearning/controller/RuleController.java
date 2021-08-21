package com.example.elearning.controller;

import com.example.elearning.model.Role;
import com.example.elearning.model.User;
import com.example.elearning.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RuleController {
    @Autowired
    RoleService roleService;
    @PostMapping("/addRole")
    public ResponseEntity<Integer> addRole(@RequestBody Role role){
       return roleService.addRole(role);
    }
    @GetMapping("/")
    public ResponseEntity<List<Role>> getAll(){
        return roleService.getAll();
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<Role> getById(@PathVariable("id") Long id){
        return roleService.getById(id);
    }
}
