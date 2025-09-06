package com.example.book.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.book.model.Admin;

public interface AdminRepository extends JpaRepository<Admin,Long> {
    boolean existsByUserName(String userName);

    Optional<Admin> findByUserNameAndPassword(String userName, String password);


}
