package com.web.bookStore.bookModule;

import com.web.bookStore.shared.entitiy.BookEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {

    List<BookEntity> searchByTitle(String title);

    List<BookEntity> findAll();

    BookEntity findById(Integer bookId);

    BookEntity save(BookEntity book);

    void remove(Integer bookId);
}
