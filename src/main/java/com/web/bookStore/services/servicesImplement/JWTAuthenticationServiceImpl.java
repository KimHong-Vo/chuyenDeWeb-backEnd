package com.web.bookStore.services.servicesImplement;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.web.bookStore.configuration.AppConfig;
import com.web.bookStore.entities.User;
import com.web.bookStore.repositories.UserRepository;
import com.web.bookStore.services.JWTAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Service
public class JWTAuthenticationServiceImpl implements JWTAuthenticationService {

    @Autowired
    private UserRepository userRepository;

    public String createAuthorizedToken(User userEntity) {
        String token = null;
        String secretKey = AppConfig.SECRET_KEY;
        String issuer = AppConfig.ISSUER;
        int timeToLive = AppConfig.TIME_TO_LIVE;
        try {
            Algorithm algorithm = Algorithm.HMAC512(secretKey);
            token = JWT.create()
                    .withIssuer(issuer)
                    .withJWTId(UUID.randomUUID().toString())
                    .withClaim("email", userEntity.getEmail())
                    .withClaim("username", userEntity.getUsername())
                    .withClaim("role", userEntity.getRole())
                    .withExpiresAt(this.setTokenTimeToLive(timeToLive))
                    .sign(algorithm);
        } catch (Exception exception) {
            throw new RuntimeException("when call JWTAuthenticationServiceImpl.createAuthorizedToken() with user email: " + userEntity.getEmail() + " -> " + exception.getMessage());
        }
        return token;
    }

    public void checkAuthorizedToken(String authorization) {
        if (Objects.isNull(authorization)) {
            throw new RuntimeException("Authorization token is null");
        }
        String jwtToken = getJwtToken(authorization);
        try {
            String secretKey = AppConfig.SECRET_KEY;
            Algorithm algorithm = Algorithm.HMAC512(secretKey);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(AppConfig.ISSUER)
                    .build();
            DecodedJWT jwt = verifier.verify(jwtToken);
        } catch (JWTVerificationException | IllegalArgumentException exception) {
            throw new RuntimeException("Authorization token is not valid");
        }
    }

    @Override
    public User getUserEmail(String authorization) {
        String email = JWT.decode(getJwtToken(authorization)).getClaim("email").asString();
        return userRepository.findById(email)
                .orElseThrow(() -> new RuntimeException("when call JWTAuthenticationServiceImpl.getUserEmail() with authorization: " + authorization));
    }

    private Date setTokenTimeToLive(int timeToLive) {
        return new Date(System.currentTimeMillis() + timeToLive);
    }

    private String getJwtToken(String authorization) {
        String[] authParts = authorization.split("\\s+");
        if (authParts.length < 2 || !"Bearer".equals(authParts[0])) {
            throw new RuntimeException("Authorization token is not valid");
        }
        return authParts[1];
    }
}
