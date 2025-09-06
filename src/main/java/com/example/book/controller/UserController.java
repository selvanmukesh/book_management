package com.example.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.book.helper.ApiResponse;
import com.example.book.helper.CustomMessage;
import com.example.book.model.User;
import com.example.book.service.UserService;
import com.example.book.util.JwtUtil;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<ApiResponse<User>> saveUser(@RequestBody User user,@RequestHeader("Authorization") String authHeader) {
        ApiResponse<User> apiResponse = new ApiResponse<>();
        try {
                 String token = authHeader.startsWith("Bearer ")
            ? authHeader.substring(7).trim()
            : authHeader.trim();            System.out.println("token---->"+token);
                        JwtUtil jwtUtil = new JwtUtil();

            String username = jwtUtil.extractUsername(token);
            

            if (!jwtUtil.validateToken(token, username)) {
                apiResponse.setMessage("Invalid token");
                apiResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
                return new ResponseEntity<>(apiResponse, HttpStatus.UNAUTHORIZED);
            }
            User userData = userService.saveUser(user);
            apiResponse.setMessage(CustomMessage.DataSavedSuccessFully);
            apiResponse.setStatus(HttpStatus.OK.value());
            apiResponse.setData(userData);

            return new ResponseEntity<>(apiResponse, HttpStatus.OK);

        } catch (Exception e) {
            apiResponse.setMessage(CustomMessage.FailedToSave + e.getMessage());
            apiResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

            return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
