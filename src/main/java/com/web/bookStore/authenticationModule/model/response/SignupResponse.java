package com.web.bookStore.authenticationModule.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupResponse {
    private String email;
    private String jwt;
}
