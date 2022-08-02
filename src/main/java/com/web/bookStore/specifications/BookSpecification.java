package com.web.bookStore.specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.web.bookStore.entities.Book;

public class BookSpecification {

	public static Specification<Book> containTitle(String title){
		return (root, query, cb) -> cb.like(root.get("title"), "%" +title + "%");
	}
	
	

}
