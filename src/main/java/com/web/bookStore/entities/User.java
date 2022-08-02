package com.web.bookStore.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
public class User implements Serializable{

	@Id
	private String email;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private boolean enabled = true;
    private int role;
//    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "user")
//    @JsonIgnore
//    Set<UserRole> userRoles = new HashSet<>();
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    @JsonIgnore
    Cart userCart;
	public String getId() {
		return email;
	}
	
	@OneToOne(mappedBy = "user")
	@JsonIgnore
	private UserJWT jwt;

	public void setId(String id) {
		this.email = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Cart getUserCart() {
		return userCart;
	}

	public void setUserCart(Cart userCart) {
		this.userCart = userCart;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "email: " + this.email + " - pass: " + this.password;
	}
    
}
