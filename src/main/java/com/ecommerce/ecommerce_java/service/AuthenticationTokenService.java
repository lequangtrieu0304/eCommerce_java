package com.ecommerce.ecommerce_java.service;

import com.ecommerce.ecommerce_java.exceptions.AuthFailException;
import com.ecommerce.ecommerce_java.exceptions.NotFoundException;
import com.ecommerce.ecommerce_java.model.AuthenticationToken;
import com.ecommerce.ecommerce_java.model.User;
import com.ecommerce.ecommerce_java.repository.TokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthenticationTokenService {
    @Autowired
    TokenRepo tokenRepo;
    public void saveToken(AuthenticationToken authenticationToken) {
        tokenRepo.save(authenticationToken);
    }

    public AuthenticationToken getToken(User user) {
        return tokenRepo.findByUser(user);
    }
    public User getUser(String token){
        AuthenticationToken authenticationToken = tokenRepo.findByToken(token);
        if(Objects.isNull(authenticationToken)){
            return null;
        }
        return authenticationToken.getUser();
    }
    public void authenticate(String token) throws NotFoundException{
        //null check
        if(Objects.isNull(token)) {
            throw new NotFoundException("Not Token");
        }
        if(Objects.isNull(getUser(token))){
            throw new AuthFailException("token not valid");
        }
    }
}
