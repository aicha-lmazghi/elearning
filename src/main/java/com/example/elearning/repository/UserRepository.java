package com.example.elearning.repository;

import com.example.elearning.model.Role;
import com.example.elearning.model.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Override
    <S extends User> S save(S s);

    @Override
    List<User> findAll();

    @Override
    List<User> findAll(Sort sort);

    @Override
    Page<User> findAll(Pageable pageable);

    Optional<User> findById(Long id);
    List<User> findByFirstName(String firstName);
    List<User> findByLastName(String lastName);
    User findByUserName(String Username);
    List<User> findByRole(Role role);

}
