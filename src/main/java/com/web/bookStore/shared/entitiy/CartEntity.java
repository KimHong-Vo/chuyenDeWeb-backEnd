package com.web.bookStore.shared.entitiy;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "cart")
public class CartEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "cart")
	private Set<CartItemEntity> cartItems = new HashSet<>();

	@OneToOne
    @JsonIgnore
    private UserEntity user;
}
