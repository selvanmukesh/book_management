package com.example.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.book.helper.ApiResponse;
import com.example.book.helper.CustomMessage;
import com.example.book.model.Admin;
import com.example.book.service.AdminService;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    @GetMapping("/login")
    public ResponseEntity<ApiResponse<String>> login(@RequestBody Admin request) {
        ApiResponse<String> apiResponse = new ApiResponse<>();
        try {
            String token = adminService.validateAdmin(request);
            apiResponse.setMessage(CustomMessage.LoginSuccess);
            apiResponse.setStatus(HttpStatus.OK.value());
            apiResponse.setData(token);

            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (Exception e) {
            apiResponse.setMessage(CustomMessage.LoginFailed +" "+ e.getMessage());
            apiResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
