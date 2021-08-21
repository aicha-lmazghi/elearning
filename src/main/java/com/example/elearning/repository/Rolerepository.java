package com.example.elearning.repository;

import com.example.elearning.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Rolerepository extends JpaRepository<Role,Long> {
    @Override
    List<Role> findAll();

    Role findByName(String name);
    Optional<Role> findById(Long id);
}
