package com.example.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.book.helper.ApiResponse;
import com.example.book.helper.CustomMessage;
import com.example.book.model.BookEntry;
import com.example.book.service.BookEntryService;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/bookEntry")
public class BookEntryController {
    @Autowired
    BookEntryService bookEntryService;

    @PostMapping
    public ResponseEntity<ApiResponse<BookEntry>> saveBookEntry(@RequestBody BookEntry bookEntry) {
        ApiResponse<BookEntry> apiResponse = new ApiResponse<>();
        try {
            BookEntry bookEntryData = bookEntryService.saveBookEntry(bookEntry);

            apiResponse.setMessage(CustomMessage.DataSavedSuccessFully);
            apiResponse.setStatus(HttpStatus.OK.value());
            apiResponse.setData(bookEntryData);

            return new ResponseEntity<>(apiResponse, HttpStatus.OK);

        } catch (Exception e) {
            apiResponse.setMessage(CustomMessage.FailedToSave + e.getMessage());
            apiResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

            return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
