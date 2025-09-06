package com.example.book.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.book.helper.CustomExcepion;
import com.example.book.model.BookEntry;
import com.example.book.repository.BookEntryRepository;

@Service
public class BookEntryService {
    @Autowired
    BookEntryRepository bookEntryRepository;

    public BookEntry saveBookEntry(BookEntry bookEntry) throws CustomExcepion {
        try {
            String newBookId;
            String lastBookId = bookEntryRepository.findLastBookId();

            if (lastBookId == null) {
                newBookId = "B0001";
            } else {
                // Extract number part from B0001
                int num = Integer.parseInt(lastBookId.substring(1));
                num++;
                newBookId = String.format("B%04d", num); // format back with 4 digits
            }

            bookEntry.setBookId(newBookId);

            return bookEntryRepository.save(bookEntry);
        } catch (Exception e) {
            // TODO: handle exception
            throw e;
        }
    }
}
