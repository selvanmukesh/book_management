package com.example.book.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.book.model.Admin;
import com.example.book.repository.AdminRepository;

@Component
public class AdminSeeder implements CommandLineRunner {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public void run(String... args) {
        if (!adminRepository.existsByUserName("admin")) {
            Admin admin = new Admin();
            admin.setUserName("admin");
            admin.setPassword("admin123");
            adminRepository.save(admin);
        }
    }
}
