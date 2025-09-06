package com.example.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.book.model.Admin;

public interface AdminRepository extends JpaRepository<Admin,Long> {
    boolean existsByUserName(String userName);

}
