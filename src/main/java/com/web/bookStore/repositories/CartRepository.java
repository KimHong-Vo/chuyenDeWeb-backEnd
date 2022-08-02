package com.web.bookStore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.web.bookStore.entities.Cart;

public interface CartRepository extends JpaRepository<Cart, Long>{

}
