package com.web.bookStore.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.web.bookStore.entities.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
