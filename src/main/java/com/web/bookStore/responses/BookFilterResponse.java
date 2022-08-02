package com.web.bookStore.responses;

import java.util.ArrayList;
import java.util.List;

import com.web.bookStore.entities.Book;

public class BookFilterResponse {
	private List<Book> books;
	private long totalItem;
	public BookFilterResponse(List<Book> books, long totalItems) {
		this.books = books;
		this.totalItem = totalItems;
	}
	public BookFilterResponse() {
		books= new ArrayList<>();
		totalItem = 0;
	}
	public List<Book> getBooks() {
		return books;
	}
	public void setBooks(List<Book> books) {
		this.books = books;
	}
	public long getTotalItem() {
		return totalItem;
	}
	public void setTotalItem(long totalItem) {
		this.totalItem = totalItem;
	}
	
	

}
