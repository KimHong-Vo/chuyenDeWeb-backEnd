package com.web.bookStore.resources;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.bookStore.entities.User;
import com.web.bookStore.entities.UserJWT;
import com.web.bookStore.services.AuthorizationService;
import com.web.bookStore.services.UserService;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserResource {
	@Autowired
	UserService uService;
	@Autowired
	AuthorizationService authService;
//	@PostMapping("/login")
//	public ResponseEntity<User> login(@RequestBody(required = true) User user) {
//		User result = uService.login(user.getEmail(), user.getPassword());
//		if(result==null)
//			return new ResponseEntity<User>(result, HttpStatus.UNAUTHORIZED);
//		else {	
//			return new ResponseEntity<User>(result, HttpStatus.OK);
//		}
//	}
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody(required = true) User user) {
		User result = uService.login(user.getEmail(), user.getPassword());
		if(result==null) {
			return new ResponseEntity<String>("", HttpStatus.UNAUTHORIZED);
		}
		else {
			String token = Base64.getEncoder().encodeToString(result.getEmail().getBytes());
			UserJWT jwt = new UserJWT();
			jwt.setCode(token);
			jwt.setUser(result);
			authService.saveJWT(jwt);
			return new ResponseEntity<String>(token , HttpStatus.OK);
		}
	}
	
	@GetMapping("/getUser")
	public ResponseEntity<User> getUserByToken(@RequestHeader("Authorization") String token) {
		System.out.println("Token: " + token);
		User result = authService.getUserByToken(token);
		if(result==null)
			return new ResponseEntity<User>(result, HttpStatus.UNAUTHORIZED);
		else {	
			return new ResponseEntity<User>(result, HttpStatus.OK);
		}
	}

	@CrossOrigin
	@PostMapping("/logout")
	public void removeToken(@RequestHeader("Authorization") String token){
		System.out.println("logout");
		authService.removeTokenById(token);
	}
}
