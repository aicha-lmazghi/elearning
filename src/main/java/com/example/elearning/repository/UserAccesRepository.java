package com.example.elearning.repository;


import com.example.elearning.model.UserAcces;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserAccesRepository extends JpaRepository<UserAcces,Long> {
    @Override
    List<UserAcces> findAll();

    @Override
    <S extends UserAcces> S save(S s);

    @Override
    void deleteById(Long aLong);

    @Override
    void delete(UserAcces accesUser);

    UserAcces findByUserName(String username);
}
