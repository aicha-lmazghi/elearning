package com.example.elearning.service.impl;

import com.example.elearning.model.Credentials;
import com.example.elearning.model.Role;
import com.example.elearning.model.SecurityInfos;
import com.example.elearning.model.User;
import com.example.elearning.repository.Rolerepository;
import com.example.elearning.repository.SecurityRepository;
import com.example.elearning.repository.UserRepository;
import com.example.elearning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    static final byte[] iv = { 0, 1, 0, 2, 0, 3, 0, 4, 0, 5, 0, 6, 0, 7, 0, 8 };
    public static final IvParameterSpec ivParameterSpec= new IvParameterSpec(iv);
    @Autowired
    UserRepository userRepository;
    @Autowired
    Rolerepository rolerepository;
    @Autowired
    SecurityRepository securityRepository;
    @Override
    public ResponseEntity<Integer> addUser(User user) throws Exception {
       List<SecurityInfos> securityInfos=securityRepository.findAll();
        SecretKey key=null;
       for(SecurityInfos securityInfos1:securityInfos) {
           key = securityInfos1.getSkey();
       }
        String algorithm = "AES/CBC/PKCS5Padding";
        String encodedPassword = encrypt(algorithm, user.getPassword(), key, ivParameterSpec);

        user.setPassword(encodedPassword);
        if(user.getRole()!=null){
            Role role=rolerepository.findByName(user.getRole().getName());
            if(role!=null){
                user.setRole(role);
            }else {
                role= rolerepository.save(user.getRole());
                user.setRole(role);
            }

        }
        User u=userRepository.save(user);
        if(u!=null){

            return new ResponseEntity<>(1, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(-1, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<User> findUserById(Long id) {
        User user=userRepository.findById(id).get();
        if(user!=null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(user, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<List<User>> findAllUsers() {
        List<User> users=userRepository.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<User>> findUserByFirstName(String firstName) {
        List<User> users=userRepository.findByFirstName(firstName);
        if(users.size()==0){
            return new ResponseEntity<>(users,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(users,HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public ResponseEntity<List<User>> findUserByLastName(String lastName) {
        List<User> users=userRepository.findByLastName(lastName);
        if(users.size()==0){
            return new ResponseEntity<>(users,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(users,HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<User> findUserByUserName(String UserName) {
        User users=userRepository.findByUserName(UserName);
        if(users!=null){
            return new ResponseEntity<>(users,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(users,HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Integer> logUser(Credentials credentials) throws Exception {
        User user=userRepository.findByUserName(credentials.getUserName());
        if(user==null){
            return new ResponseEntity<>(-2,HttpStatus.NOT_FOUND);
        }else if(user.isLoggedIn()){
            return new ResponseEntity<>(-3,HttpStatus.BAD_REQUEST);
        }
        List<SecurityInfos> securityInfos=securityRepository.findAll();
        SecretKey key=null;
        for(SecurityInfos securityInfos1:securityInfos) {
            key = securityInfos1.getSkey();
        }

        String algorithm = "AES/CBC/PKCS5Padding";
        String password=decrypt(algorithm,user.getPassword(),key,ivParameterSpec);
        if(credentials.getPassword().equalsIgnoreCase(password)){
            user.setLoggedIn(true);
            userRepository.save(user);
        return new ResponseEntity<>(1,HttpStatus.OK);
        }else  {
            return new ResponseEntity<>(-1,HttpStatus.FORBIDDEN);
        }
    }

    @Override
    public ResponseEntity<Integer> logOutUser(Long id) {
        Optional<User> user=userRepository.findById(id);
        if(user.isPresent()){
            User user1=user.get();
            if(user1.isLoggedIn()){
                user1.setLoggedIn(false);
                userRepository.save(user1);
                return new ResponseEntity<>(1,HttpStatus.OK);
            }else{
                return new ResponseEntity<>(-2,HttpStatus.BAD_REQUEST);
            }
        }else{
            return new ResponseEntity<>(-1,HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public ResponseEntity<List<User>> findUserByRoleName(String roleName) {
        Role role=rolerepository.findByName(roleName);
        List<User> users=userRepository.findByRole(role);

    return new ResponseEntity<>(users,HttpStatus.OK);

    }

    public static String encrypt(String algorithm, String input, SecretKey key,
                                 IvParameterSpec iv) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException {

        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, key, iv);
        byte[] cipherText = cipher.doFinal(input.getBytes());
        return Base64.getEncoder().encodeToString(cipherText);

    }
    public static String decrypt(String algorithm, String cipherText, SecretKey key,
                                 IvParameterSpec iv) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException {

        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, key, iv);
        byte[] plainText = cipher.doFinal(Base64.getDecoder().decode(cipherText));
        return new String(plainText);
    }


}
