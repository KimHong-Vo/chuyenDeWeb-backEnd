package com.web.bookStore.jwtModule;

import com.web.bookStore.shared.entitiy.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface JwtAuthenticationRepository extends CrudRepository<UserEntity, String> {

}
