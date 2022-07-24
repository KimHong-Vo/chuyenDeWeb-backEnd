package com.web.bookStore.dto;

import java.util.Set;

public class CartDTO {
	private long idCart;
	private Set<CartItemDTO> cartItem;
	public long getIdCart() {
		return idCart;
	}
	public void setIdCart(long idCart) {
		this.idCart = idCart;
	}
	public Set<CartItemDTO> getCartItem() {
		return cartItem;
	}
	public void setCartItem(Set<CartItemDTO> cartItem) {
		this.cartItem = cartItem;
	}
	public CartDTO(long idCart, Set<CartItemDTO> cartItem) {
		super();
		this.idCart = idCart;
		this.cartItem = cartItem;
	}
	

}
