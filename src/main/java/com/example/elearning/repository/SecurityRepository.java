package com.example.elearning.repository;

import com.example.elearning.model.SecurityInfos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SecurityRepository extends JpaRepository<SecurityInfos,Long> {
    @Override
    List<SecurityInfos> findAll();

    @Override
    <S extends SecurityInfos> S save(S s);
}
