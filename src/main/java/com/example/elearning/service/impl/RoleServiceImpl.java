package com.example.elearning.service.impl;

import com.example.elearning.model.Role;
import com.example.elearning.repository.Rolerepository;
import com.example.elearning.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    Rolerepository rolerepository;
    @Override
    public ResponseEntity<Role> getById(Long id) {
        Role role=rolerepository.findById(id).get();
        if(role!=null) {
            return new ResponseEntity<>(role, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(role, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<List<Role>> getAll() {
        List<Role> roles=rolerepository.findAll();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Integer> addRole(Role role) {
        Role role1=rolerepository.save(role);
        if(role1!=null){
            return new ResponseEntity<>(1, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(-1, HttpStatus.FORBIDDEN);
        }

    }
}
