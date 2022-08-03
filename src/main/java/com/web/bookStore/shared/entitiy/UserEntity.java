package com.web.bookStore.shared.entitiy;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "user")
public class UserEntity implements Serializable{
	@Id
	private String email;
    private String username;
    private String password;
    private String phone;
    private boolean enabled = true;
	private int role;

//    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "user")
//    @JsonIgnore
//    Set<UserRole> userRoles = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    @JsonIgnore
    private CartEntity userCart;
	
	@Override
	public String toString() {
		return "email: " + this.email + " - pass: " + this.password;
	}
}
