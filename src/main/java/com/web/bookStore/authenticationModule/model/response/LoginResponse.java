package com.web.bookStore.authenticationModule.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    private String email;
    private String username;
    private String jwt;
}
