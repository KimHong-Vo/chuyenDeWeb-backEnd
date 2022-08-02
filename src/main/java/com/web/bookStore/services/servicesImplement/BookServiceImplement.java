package com.web.bookStore.services.servicesImplement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.support.Repositories;
import org.springframework.stereotype.Service;

import com.web.bookStore.entities.Book;
import com.web.bookStore.entities.CartItem;
import com.web.bookStore.repositories.BookRepository;
import com.web.bookStore.requests.BookFilterRequest;
import com.web.bookStore.responses.BookFilterResponse;
import com.web.bookStore.services.BookService;
import com.web.bookStore.specifications.BookSpecification;

@Service
public class BookServiceImplement implements BookService{
	@Autowired
	BookRepository bookRepository;
	@Autowired
	private EntityManager entityManager;

	@Transactional
	public Book update(Book book) {
		return entityManager.merge(book);
	}
	
	@Override
	public List<Book> findAll() {// only find book have been actived
		int page =0;
		int size = 16;
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

	@Override
	public BookFilterResponse findByFilter(BookFilterRequest request) {
		// TODO Auto-generated method stub
//		Page<Book> pageItem = bookRepository.findAll(PageRequest.of(request.getPageIndex(), request.getPageSize(), Sort.by(Sort.Direction.ASC, "price")));
//      BookSpecification b = new BookSpecification();
		List<Book> books = new ArrayList<Book>();
		if(request.getTitlePart()!= null) {
			switch(request.getOrderPriceFilter()) {
			case 0: books = bookRepository.findAll(BookSpecification.containTitle(request.getTitlePart()), 
					PageRequest.of(request.getPageIndex(), request.getPageSize(), Sort.by(Sort.Direction.ASC, "ourPrice"))).getContent();
				break;
			case 1: books = bookRepository.findAll(BookSpecification.containTitle(request.getTitlePart()), 
					PageRequest.of(request.getPageIndex(), request.getPageSize(), Sort.by(Sort.Direction.DESC, "ourPrice"))).getContent();
				break;
			default:  books = bookRepository.findAll(BookSpecification.containTitle(request.getTitlePart()), 
					PageRequest.of(request.getPageIndex(), request.getPageSize())).getContent();
				break;
			}
		}
		else {
			switch(request.getOrderPriceFilter()) {
			case 0: books = bookRepository.findAll( 
					PageRequest.of(request.getPageIndex(), request.getPageSize(), Sort.by(Sort.Direction.ASC, "ourPrice"))).getContent();
				break;
			case 1: books = bookRepository.findAll(
					PageRequest.of(request.getPageIndex(), request.getPageSize(), Sort.by(Sort.Direction.DESC, "ourPrice"))).getContent();
				break;
			default:  books = bookRepository.findAll(
					PageRequest.of(request.getPageIndex(), request.getPageSize())).getContent();
				break;
			}
			
		}
		return new BookFilterResponse(books, bookRepository.count(BookSpecification.containTitle(request.getTitlePart())));
	}
	

}
