package com.web.bookStore.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.bookStore.entities.User;
import com.web.bookStore.services.UserService;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserResource {
	@Autowired
	UserService uService;
	@PostMapping("/login")
	public ResponseEntity<User> login(@RequestBody(required = true) User user) {
		User result = uService.login(user.getEmail(), user.getPassword());
		if(result==null)
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		else {	
			return new ResponseEntity<User>(result, HttpStatus.OK);
		}
	}

}
