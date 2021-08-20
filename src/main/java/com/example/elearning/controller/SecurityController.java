package com.example.elearning.controller;

import com.example.elearning.model.SecurityInfos;
import com.example.elearning.model.User;
import com.example.elearning.service.SecurityService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/security")
public class SecurityController {
    @Autowired
    SecurityService securityService;
    @SneakyThrows
    @PostMapping("/saveInfos")
    public ResponseEntity<Integer> addInfos() {
        return securityService.saveInfos();
    }
    @GetMapping("/")
    public ResponseEntity<List<SecurityInfos>> getAll(){
        return securityService.getSecurityInfos();
    }
}
