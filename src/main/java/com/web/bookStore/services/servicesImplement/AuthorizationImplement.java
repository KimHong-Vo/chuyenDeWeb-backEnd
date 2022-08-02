package com.web.bookStore.services.servicesImplement;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.bookStore.entities.User;
import com.web.bookStore.entities.UserJWT;
import com.web.bookStore.repositories.UserJWTRepository;
import com.web.bookStore.services.AuthorizationService;

@Service
public class AuthorizationImplement implements AuthorizationService{

	@Autowired
	UserJWTRepository userJWTRepo;
	@Override
	public boolean checkToken(String token) {
		Optional<UserJWT> jwt = userJWTRepo.findById(token);
		if(jwt.isEmpty())
		return false;
		else
			return true;
	}

	@Override
	public User getUserByToken(String tokenId) {
		Optional<UserJWT> jwt = userJWTRepo.findById(tokenId);
		return jwt.get().getUser();
	}

	@Override
	public UserJWT getJWTById(String tokenId) {
		Optional<UserJWT> jwt = userJWTRepo.findById(tokenId);
		if(jwt.isEmpty())
		return null;
		else
			return jwt.get();
	}

	@Override
	public void saveJWT(UserJWT u) {
		// TODO Auto-generated method stub
		userJWTRepo.save(u);
	}

	@Override
	public void removeTokenById(String tokenId) {
		userJWTRepo.deleteById(tokenId);
	}

}
