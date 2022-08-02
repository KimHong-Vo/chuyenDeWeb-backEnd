package com.web.bookStore.services.servicesImplement;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.bookStore.entities.Cart;
import com.web.bookStore.entities.CartItem;
import com.web.bookStore.repositories.CartRepository;
import com.web.bookStore.services.CartService;

@Service
public class CartServiceImplement implements CartService{
	@Autowired
	private CartRepository repo;
	   

	@Override
	public Optional<Cart> getCartById(long id) {
		return repo.findById(id);
	}



	

}
