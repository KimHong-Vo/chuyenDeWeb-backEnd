package com.web.bookStore.authenticationModule.mapper;

import com.web.bookStore.authenticationModule.model.response.LoginResponse;
import com.web.bookStore.shared.entitiy.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LoginMapper {

    LoginResponse mapUserEntityToLoginResponse(UserEntity userEntity);
}
