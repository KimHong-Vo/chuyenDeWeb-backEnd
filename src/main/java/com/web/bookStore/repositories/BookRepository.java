package com.web.bookStore.repositories;

import java.util.List;
import java.util.Set;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.web.bookStore.entities.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book>{
	@Query(value = "select * from book where book.title like %:title% limit 6", nativeQuery = true)
	List<Book> findByTitle(@Param("title") String title);
	
    Set<Book> findByTitleContaining(String keyword);
}
