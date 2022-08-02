package com.web.bookStore.repositories;

import org.springframework.data.repository.CrudRepository;

import com.web.bookStore.entities.UserJWT;

public interface UserJWTRepository extends CrudRepository<UserJWT, String>{

}
