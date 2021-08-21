package com.example.elearning.service.impl;

import com.example.elearning.model.SecurityInfos;
import com.example.elearning.repository.SecurityRepository;
import com.example.elearning.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
public class SecurityServiceImpl implements SecurityService {

    @Autowired
    SecurityRepository securityRepository;


    @Override
    public ResponseEntity<List<SecurityInfos>> getSecurityInfos() {
        List<SecurityInfos> securityInfos=securityRepository.findAll();
        return new ResponseEntity<>(securityInfos,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Integer> saveInfos() throws Exception{
        List<SecurityInfos> securityInfos1=getSecurityInfos().getBody();
        if(securityInfos1.size()!=0){
            return new ResponseEntity<>(-1, HttpStatus.BAD_REQUEST);
        }else{
            SecurityInfos securityInfos=new SecurityInfos();
        SecretKey key = generateKey(128);
        IvParameterSpec ivParameterSpec = generateIv();

            securityInfos.setSkey(key);

            SecurityInfos securityInfos3=securityRepository.save(securityInfos);
            return new ResponseEntity<>(1, HttpStatus.OK);
        }

    }


    public static SecretKey generateKey(int n) throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(n);
        SecretKey key = keyGenerator.generateKey();
        return key;
    }
    public static IvParameterSpec generateIv() {
        byte[] iv = new byte[16];
        new SecureRandom().nextBytes(iv);
        return new IvParameterSpec(iv);
    }
}
