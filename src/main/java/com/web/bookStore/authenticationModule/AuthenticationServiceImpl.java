package com.web.bookStore.authenticationModule;

import com.web.bookStore.authenticationModule.exception.UnauthorizedException;
import com.web.bookStore.authenticationModule.exception.WrongPasswordException;
import com.web.bookStore.authenticationModule.mapper.LoginMapper;
import com.web.bookStore.authenticationModule.model.request.LoginRequest;
import com.web.bookStore.authenticationModule.model.request.SignupRequest;
import com.web.bookStore.authenticationModule.model.response.LoginResponse;
import com.web.bookStore.authenticationModule.model.response.SignupResponse;
import com.web.bookStore.shared.entitiy.UserEntity;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Objects;

@Log4j2
@Component
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private AuthenticationRepository authenticationRepository;

    @Autowired
    private LoginMapper loginMapper;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        UserEntity foundUser = authenticationRepository.findByEmail(loginRequest.getEmail());
        // check null
        if (Objects.isNull(foundUser)) {
            throw new UnauthorizedException();
        }
        // check password
        if (!loginRequest.getPassword().equals(foundUser.getPassword())) {
            throw new WrongPasswordException();
        }
        log.info("Login successfully");
        return loginMapper.mapUserEntityToLoginResponse(foundUser);
    }

    @Override
    public SignupResponse signup(SignupRequest signupRequest) {
        return null;
    }
}
