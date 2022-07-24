package com.web.bookStore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.bookStore.entities.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long>{

}
