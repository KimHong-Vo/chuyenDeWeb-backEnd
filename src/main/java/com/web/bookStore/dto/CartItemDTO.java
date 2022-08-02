package com.web.bookStore.dto;

public class CartItemDTO {
	private long id;
	private int quantities;
	private BookDTO book;

	public int getQuantities() {
		return quantities;
	}

	public void setQuantities(int quantities) {
		this.quantities = quantities;
	}

	public BookDTO getBook() {
		return book;
	}

	public void setBook(BookDTO book) {
		this.book = book;
	}
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public CartItemDTO(int quantities, BookDTO book) {
		super();
		this.quantities = quantities;
		this.book = book;
	}

	
}
