package com.web.bookStore.services;

import com.web.bookStore.entities.User;

public interface UserService {
	public User login(String email, String password);
}
