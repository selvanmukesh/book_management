package com.example.book.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.book.helper.CustomExcepion;
import com.example.book.helper.CustomMessage;
import com.example.book.model.BookEntry;
import com.example.book.model.User;
import com.example.book.repository.BookEntryRepository;
import com.example.book.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    BookEntryRepository bookEntryRepository;

    public User saveUser(User user) throws CustomExcepion {
        try {
            BookEntry bookEntry = bookEntryRepository.findByBookId(user.getBookId())
                    .orElseThrow(() -> new CustomExcepion(CustomMessage.BookNotFound + "--" + user.getBookId()));

            if (bookEntry.getNumberOfCopiesAvailable() < 0) {
                throw new CustomExcepion(CustomMessage.NoCopiesAvailable+"---"+user.getBookId());
            }
            User userData = userRepository.save(user);
            bookEntryRepository.decrementCopies(user.getBookId());
            return userData;
        } catch (Exception e) {
            // TODO: handle exception
            throw new CustomExcepion(e.getMessage());
        }
    }
}
