package com.web.bookStore.authenticationModule;

import com.web.bookStore.authenticationModule.model.request.LoginRequest;
import com.web.bookStore.authenticationModule.model.response.LoginResponse;
import com.web.bookStore.authenticationModule.model.request.SignupRequest;
import com.web.bookStore.authenticationModule.model.response.SignupResponse;
import org.springframework.stereotype.Service;

@Service
interface AuthenticationService {

    LoginResponse login(LoginRequest loginRequest);

    SignupResponse signup(SignupRequest signupRequest);
}
