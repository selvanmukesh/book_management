package com.example.book.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.book.model.BookEntry;

import jakarta.transaction.Transactional;


@Repository
public interface BookEntryRepository  extends JpaRepository<BookEntry,Long>{
    @Query("SELECT b.bookId FROM BookEntry b ORDER BY b.bookId DESC LIMIT 1")
    String findLastBookId();

    Optional<BookEntry> findByBookId(String bookId);

@Modifying
@Transactional
@Query("UPDATE BookEntry b SET b.numberOfCopiesAvailable = b.numberOfCopiesAvailable - 1 WHERE b.bookId = :bookId")
int decrementCopies(@Param("bookId") String bookId);
}
