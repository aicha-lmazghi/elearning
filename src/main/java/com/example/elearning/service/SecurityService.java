package com.example.elearning.service;

import com.example.elearning.model.SecurityInfos;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SecurityService {

    ResponseEntity<List<SecurityInfos>> getSecurityInfos();
    ResponseEntity<Integer> saveInfos() throws Exception;
}
