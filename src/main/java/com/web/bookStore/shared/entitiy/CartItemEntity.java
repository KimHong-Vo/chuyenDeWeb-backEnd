package com.web.bookStore.shared.entitiy;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "cart_item")
public class CartItemEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	private int quantity;
	
	@ManyToOne
    @JoinColumn(name = "book_id")
    private BookEntity book;
	
	@ManyToOne
	@JoinColumn(name = "cart_id")
	private CartEntity cart;
}
