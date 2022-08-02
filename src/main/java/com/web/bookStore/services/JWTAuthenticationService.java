package com.web.bookStore.services;

import com.web.bookStore.entities.User;

public interface JWTAuthenticationService {

     String createAuthorizedToken(User userEntity);

     void checkAuthorizedToken(String authorization);

     User getUserEmail(String authorization);
}
