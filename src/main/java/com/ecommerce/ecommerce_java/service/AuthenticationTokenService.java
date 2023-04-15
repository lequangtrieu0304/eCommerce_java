package com.ecommerce.ecommerce_java.service;

import com.ecommerce.ecommerce_java.exceptions.AuthException;
import com.ecommerce.ecommerce_java.exceptions.NotFoundException;
import com.ecommerce.ecommerce_java.model.AuthenticationTokens;
import com.ecommerce.ecommerce_java.model.User;
import com.ecommerce.ecommerce_java.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthenticationTokenService {
    @Autowired
    TokenRepository tokenRepository;
    public void saveToken(AuthenticationTokens authenticationTokens) {
        tokenRepository.save(authenticationTokens);
    }

    public AuthenticationTokens getToken(User user) {
        return tokenRepository.findByUser(user);
    }
    public User getUser(String token){
        AuthenticationTokens authenticationTokens = tokenRepository.findByToken(token);
        if(Objects.isNull(authenticationTokens)){
            return null;
        }
        return authenticationTokens.getUser();
    }
    public void authenticate(String token) throws NotFoundException{
        //null check
        if(Objects.isNull(token)) {
            throw new NotFoundException("Not Token");
        }
        if(Objects.isNull(getUser(token))){
            throw new AuthException("token not valid");
        }
    }
}
