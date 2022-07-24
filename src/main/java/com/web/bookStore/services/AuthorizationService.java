package com.web.bookStore.services;

import com.web.bookStore.entities.User;
import com.web.bookStore.entities.UserJWT;

public interface AuthorizationService {
	boolean checkToken(String token);
	User getUserByToken(String tokenId);
	UserJWT getJWTById(String tokenId);
	void saveJWT(UserJWT u);
	void removeTokenById(String tokenId);
}
