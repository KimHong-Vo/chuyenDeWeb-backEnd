package com.web.bookStore.services;

import java.util.Optional;

import com.web.bookStore.entities.Cart;

public interface CartService {
  Optional<Cart> getCartById(long id);
}
