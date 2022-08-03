package com.web.bookStore.jwtModule;

import com.web.bookStore.shared.entitiy.UserEntity;

public interface JWTAuthenticationService {

     String createAuthorizedToken(UserEntity userEntity);

     void checkAuthorizedToken(String authorization);

     UserEntity getUserByEmail(String authorization);
}
