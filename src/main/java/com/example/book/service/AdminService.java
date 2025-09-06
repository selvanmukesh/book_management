package com.example.book.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.book.helper.CustomExcepion;
import com.example.book.helper.CustomMessage;
import com.example.book.model.Admin;
import com.example.book.repository.AdminRepository;
import com.example.book.util.JwtUtil;

@Service
public class AdminService {
    @Autowired
    AdminRepository adminRepository;

    public String validateAdmin(Admin request) throws CustomExcepion {

        try {
            Admin admin = adminRepository.findByUserNameAndPassword(request.getUserName(), request.getPassword())
                    .orElseThrow(() -> new CustomExcepion(CustomMessage.InvalidCrediantials));
            JwtUtil jwtUtil = new JwtUtil();
            String token = jwtUtil.generateToken(admin.getUserName());
            return token;
        } catch (Exception e) {
            // TODO: handle exception
            throw new CustomExcepion(e.getMessage());
        }

    }
}
