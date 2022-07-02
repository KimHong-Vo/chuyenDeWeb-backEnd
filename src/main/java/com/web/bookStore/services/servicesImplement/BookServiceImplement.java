package com.web.bookStore.services.servicesImplement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.bookStore.entities.Book;
import com.web.bookStore.repositories.BookRepository;
import com.web.bookStore.services.BookService;

@Service
public class BookServiceImplement implements BookService{
	@Autowired
	BookRepository bookRepository;
	
	@Override
	public List<Book> findAll() {// only find book have been actived
		List<Book> books = (List<Book>) bookRepository.findAll();
        List<Book> activeBook = new ArrayList<>();

        for (Book book : books) {
            if (book.isActive()) {
                activeBook.add(book);
            }
        }
        return activeBook;
	}

	@Override
	public Optional<Book> findOne(long id) {
		// TODO Auto-generated method stub
		return bookRepository.findById(id);
	}

	@Override
	public List<Book> blurrysearch(String title) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void removeOne(Long id) {
		bookRepository.deleteById(id);
		return null;
	}

	@Override
	public List<Book> blurrySearch(String title) {// find books activated by title
		List<Book> books = bookRepository.findByTitle(title);
        List<Book> activeBookbytitle = new ArrayList<Book>();

        for (Book book : books) {
            if (book.isActive()) {
                activeBookbytitle.add(book);
            }
        }
        return activeBookbytitle;
	}

	@Override
	public Book saveBook(Book book) {
		// TODO Auto-generated method stub
		return bookRepository.save(book);
	}

}
