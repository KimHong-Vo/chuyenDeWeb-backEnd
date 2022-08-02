package com.web.bookStore.services.servicesImplement;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.bookStore.entities.CartItem;
import com.web.bookStore.repositories.CartItemRepository;
import com.web.bookStore.services.CartItemService;

@Service
public class CartItemImplement implements CartItemService {

	@Autowired
	CartItemRepository repo;
	@Autowired
	private EntityManager entityManager;

	@Transactional
	public CartItem update(CartItem cartItem) {
		return entityManager.merge(cartItem);
	}

	public void saveCartItem(CartItem item) {
		repo.save(item);
	}

	@Override
	public void remove(long idCartItem) {
		repo.deleteById(idCartItem);

	}

	
	@Override
	public CartItem getCartItem(long cartItemId) {

		return repo.findById(cartItemId).get();
	}

}
