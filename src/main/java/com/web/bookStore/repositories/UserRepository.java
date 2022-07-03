package com.web.bookStore.repositories;

import org.springframework.data.repository.CrudRepository;

import com.web.bookStore.entities.User;

public interface UserRepository extends CrudRepository<User, String>{

}
