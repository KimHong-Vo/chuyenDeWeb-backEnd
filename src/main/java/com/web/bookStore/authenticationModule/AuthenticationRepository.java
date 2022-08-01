package com.web.bookStore.authenticationModule;

import com.web.bookStore.shared.entitiy.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface AuthenticationRepository extends CrudRepository<UserEntity, Integer> {

    UserEntity findByEmail(String email);
}
