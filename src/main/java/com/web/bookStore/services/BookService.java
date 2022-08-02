package com.web.bookStore.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.web.bookStore.entities.Book;
import com.web.bookStore.requests.BookFilterRequest;
import com.web.bookStore.responses.BookFilterResponse;

public interface BookService{
	List<Book> findAll();
	
	Optional<Book> findOne(long id);
	 
	List<Book> blurrysearch(String title);

    Void removeOne(Long id);

    List<Book> blurrySearch(String title);
    
    Book saveBook(Book book);
    
    BookFilterResponse findByFilter(BookFilterRequest request);
}