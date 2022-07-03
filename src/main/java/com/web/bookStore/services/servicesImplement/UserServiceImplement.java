package com.web.bookStore.services.servicesImplement;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.bookStore.entities.User;
import com.web.bookStore.repositories.UserRepository;
import com.web.bookStore.services.UserService;

@Service
public class UserServiceImplement implements UserService{
	@Autowired
	UserRepository uRepo;
	@Override
	public User login(String email, String password) {
		Optional<User> u = uRepo.findById(email);
		if(u==null || u.isEmpty()) {
		return null;
		}
		else if (u.get().getPassword().equals(password)) {
			return u.get();
		}
		return null;
	}

}
