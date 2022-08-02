package com.web.bookStore.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.web.bookStore.entities.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
	List<Book> findByTitle(String title);

	Set<Book> findByTitleContaining(String keyword);

}
