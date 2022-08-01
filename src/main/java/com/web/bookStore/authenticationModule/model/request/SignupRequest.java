package com.web.bookStore.authenticationModule.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequest {
    private String email;
    private String password;
    private String username;
    private String phone;
}
