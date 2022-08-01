package com.web.bookStore.bookModule;

import com.web.bookStore.shared.entitiy.BookEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<BookEntity> searchByTitle(String title) {
        List<BookEntity> activeBookList = new ArrayList<>();
        bookRepository.searchByTitle(title)
                .forEach(book -> {
                    if (book.isActive()) {
                        activeBookList.add(book);
                    }
                });
        return activeBookList;
    }

    @Override
    public List<BookEntity> findAll() {
        List<BookEntity> activeBookList = new ArrayList<>();
        bookRepository.findAll()
                .forEach(book -> {
                    if (book.isActive()) {
                        activeBookList.add(book);
                    }
                });
        return activeBookList;
    }

    @Override
    public BookEntity findById(Integer bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException(String.format("Could not find book with id: %s", bookId)));
    }

    @Override
    public BookEntity save(BookEntity book) {
        return bookRepository.save(book);
    }

    @Override
    public void remove(Integer bookId) {
        bookRepository.deleteById(bookId);
    }
}
