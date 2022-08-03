package com.web.bookStore.resources;

import com.web.bookStore.entities.User;
import com.web.bookStore.services.AuthorizationService;
import com.web.bookStore.services.JWTAuthenticationService;
import com.web.bookStore.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserResource {
    @Autowired
    UserService uService;
    @Autowired
    AuthorizationService authService;
    @Autowired
    private JWTAuthenticationService jwtAuthenticationService;

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
        User foundUser = uService.login(user.getEmail(), user.getPassword());
        if (foundUser == null) {
            return new ResponseEntity<String>("", HttpStatus.UNAUTHORIZED);
        } else {
            return new ResponseEntity<String>(jwtAuthenticationService.createAuthorizedToken(foundUser), HttpStatus.OK);
        }
    }

    @GetMapping("/getUser")
    public ResponseEntity<User> getUserByToken(@RequestHeader("Authorization") String token) {
        System.out.println("Token: " + token);
        User result = jwtAuthenticationService.getUserEmail(token);
        if (result == null)
            return new ResponseEntity<User>(HttpStatus.UNAUTHORIZED);
        else {
            return new ResponseEntity<User>(result, HttpStatus.OK);
        }
    }
    @GetMapping("/checkExistEmail/{email}")
    public ResponseEntity<Boolean> isEmailExist(@PathVariable(name = "email") String email) {
    	User u = uService.findUserByID(email);
    	return u!=null? new ResponseEntity<Boolean>(true, HttpStatus.OK) : new ResponseEntity<Boolean>(false, HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping("/logout")
    public void removeToken(@RequestHeader("Authorization") String token) {
        System.out.println("logout");
        authService.removeTokenById(token);
    }
}
