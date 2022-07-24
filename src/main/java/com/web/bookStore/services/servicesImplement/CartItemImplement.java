package com.web.bookStore.services.servicesImplement;

import org.springframework.beans.factory.annotation.Autowired;

import com.web.bookStore.entities.CartItem;
import com.web.bookStore.repositories.CartItemRepository;
import com.web.bookStore.services.CartItemService;

public class CartItemImplement implements CartItemService {

	@Autowired
	CartItemRepository repo;
	public void saveCartItem(CartItem item) {
		repo.save(item);
	}

}
