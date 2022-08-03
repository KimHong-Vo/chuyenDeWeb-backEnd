package com.web.bookStore.authenticationModule;

import com.web.bookStore.authenticationModule.exception.UnauthorizedException;
import com.web.bookStore.authenticationModule.exception.WrongPasswordException;
import com.web.bookStore.authenticationModule.model.request.LoginRequest;
import com.web.bookStore.jwtModule.JWTAuthenticationService;
import com.web.bookStore.shared.entitiy.UserEntity;
import com.web.bookStore.shared.model.ResponseObject;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.web.bookStore.authenticationModule.AuthenticationConstant.Message.*;

@Log4j2
@CrossOrigin
@RestController
@RequestMapping(path = "auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private AuthenticationRepository authenticationRepository;

    @PostMapping(path = "login")
    public ResponseEntity<ResponseObject> login(@RequestBody(required = true) LoginRequest loginRequest) {
        try {
            UserEntity foundUser = authenticationRepository.findByEmail(loginRequest.getEmail());
            if (foundUser.getRole() != 1) {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new ResponseObject(NOT_ACCEPTABLE));
            }
            log.info(String.format("Login with email: %s", loginRequest.getEmail()));
            return ResponseEntity.ok(new ResponseObject(SUCCESSFULLY, authenticationService.login(loginRequest)));
        } catch (UnauthorizedException exception) {
            log.info(String.format("Login fail as could not found email: %s", loginRequest.getEmail()));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(UNAUTHORIZED));
        } catch (WrongPasswordException exception) {
            log.info("Login fail as wrong password");
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new ResponseObject(WRONG_PASSWORD));
        }
    }
}
