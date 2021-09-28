package com.example.elearning.service.impl;

import com.example.elearning.config.Utils;
import com.example.elearning.model.*;

import com.example.elearning.repository.Rolerepository;
import com.example.elearning.repository.SecurityRepository;
import com.example.elearning.repository.UserAccesRepository;
import com.example.elearning.repository.UserRepository;
import com.example.elearning.service.UserServices;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.transaction.Transactional;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserServices {
    static final byte[] iv = { 0, 1, 0, 2, 0, 3, 0, 4, 0, 5, 0, 6, 0, 7, 0, 8 };
    public static final IvParameterSpec ivParameterSpec= new IvParameterSpec(iv);
    @Autowired
    UserRepository userRepository;
    @Autowired
    Rolerepository rolerepository;
    @Autowired
    SecurityRepository securityRepository;
    @Autowired
    UserAccesRepository accesUserRepository;
    @Autowired
    Utils util;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public ResponseEntity<Integer> addUser(UserDto userdto) throws Exception {
        String encodedPassword = bCryptPasswordEncoder.encode(userdto.getPassword());
        UserAcces accesUser=new UserAcces(userdto.getUserName(),encodedPassword,userdto.getMail());
        //UserAcces accesUser1=accesUserRepository.save(accesUser);
        User user=new User("", userdto.getFirstName(), userdto.getLastName(), userdto.getFullNumber(), userdto.getImage(), userdto.getDateNaiss(),accesUser);
        Role role=rolerepository.findByName(userdto.getRole());
        Role role1=new Role(userdto.getRole(),"");
        if(role==null){
            role1= rolerepository.save(role1);
            user.setRole(role1);
        }else{
            user.setRole(role);
        }
        user.setIdUser(util.generateStringId(32));
        User u=userRepository.save(user);
        if(u!=null ){

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
    public ResponseEntity<User> findUserByUserId(String iduser) {
        User user=userRepository.findByIdUser(iduser);
        return null;
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
    public ResponseEntity<UserDto> findUserByUserName(String userName) {
        UserAcces userAcces=accesUserRepository.findByUserName(userName);
        User users=userRepository.findByAccesUser(userAcces);
        UserDto userDto=new UserDto();
        if(users!=null && userAcces!=null){
           // UserDto userDto=new UserDto(users.getIdUser(),userAcces.getUserName(),userAcces.getEncryptedpassword()
            //        ,users.getFirstName(),users.getLastName(),userAcces.getMail(),users.getFullNumber(),users.getImage()
            //        ,users.getDateNaiss(),users.getRole().getName());
            BeanUtils.copyProperties(users,userDto);
            return new ResponseEntity<>(userDto,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Integer> logUser(AccesUserDto accesUserdto) throws Exception {
        UserAcces accesUser=accesUserRepository.findByUserName(accesUserdto.getUserName());
        if(accesUser==null){
            return new ResponseEntity<>(-2,HttpStatus.NOT_FOUND);
        }else if(accesUser.isLoggedIn()){
            return new ResponseEntity<>(-3,HttpStatus.BAD_REQUEST);
        }
        List<SecurityInfos> securityInfos=securityRepository.findAll();
        SecretKey key=null;
        for(SecurityInfos securityInfos1:securityInfos) {
            key = securityInfos1.getSkey();
        }

        String algorithm = "AES/CBC/PKCS5Padding";
        String password=decrypt(algorithm,accesUser.getEncryptedpassword(), key,ivParameterSpec);
        if(accesUserdto.getPassword().equalsIgnoreCase(password)){
            accesUser.setLoggedIn(true);
            accesUserRepository.save(accesUser);
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
            UserAcces accesUser=user1.getAccesUser();
            if(accesUser.isLoggedIn()){
                accesUser.setLoggedIn(false);
                accesUserRepository.save(accesUser);
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

    @Override
    @Transactional
    public ResponseEntity<Integer> deleteUserById(String id) {
        User user=userRepository.findByIdUser(id);
        UserAcces userAcces=user.getAccesUser();
        if(user!=null && userAcces!=null){
            accesUserRepository.delete(userAcces);
            userRepository.delete(user);
            return new ResponseEntity<>(1,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(-1,HttpStatus.BAD_REQUEST);
        }


    }

    @Override
    public ResponseEntity<Integer> updateUser(UserDto userdto) {
        User user=userRepository.findByIdUser(userdto.getIdUser());
        return null;
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


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserAcces userAcces=accesUserRepository.findByUserName(s);
        User user=userRepository.findByAccesUser(userAcces);
        if(user==null){
            throw new UsernameNotFoundException(s);
        }else{
            return new org.springframework.security.core.userdetails.User(userAcces.getUserName(),userAcces.getEncryptedpassword(),new ArrayList<>());
        }

    }
}
