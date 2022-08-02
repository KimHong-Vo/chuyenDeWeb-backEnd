package com.web.bookStore.services;

import com.web.bookStore.entities.CartItem;

public interface CartItemService {
	public void saveCartItem(CartItem item);
	public void remove(long idCartItem);
	public CartItem getCartItem(long cartItemId);
	public CartItem update(CartItem cartItem);

}
