package com.ecommerce.ecommerce_java.service;

import com.ecommerce.ecommerce_java.dto.ResponseDto;
import com.ecommerce.ecommerce_java.dto.user.SignInDto;
import com.ecommerce.ecommerce_java.dto.user.SigninResponseDto;
import com.ecommerce.ecommerce_java.dto.user.SignUpDto;
import com.ecommerce.ecommerce_java.exceptions.AuthException;
import com.ecommerce.ecommerce_java.exceptions.CustomException;
import com.ecommerce.ecommerce_java.exceptions.NotFoundException;
import com.ecommerce.ecommerce_java.model.AuthenticationTokens;
import com.ecommerce.ecommerce_java.model.User;
import com.ecommerce.ecommerce_java.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthenticationTokenService authenticationTokenService;
    public ResponseDto signUp(SignUpDto signUpDto) throws NoSuchAlgorithmException, CustomException {
        User user = userRepository.findByEmail(signUpDto.getEmail());
        if(Objects.nonNull(user)){
            throw new CustomException("user aleady present");
        }

        String encryptedpassword = signUpDto.getPassword();
        try{
            encryptedpassword = hashPassword(signUpDto.getPassword());
        }
        catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }

        User createUser = new User(
                signUpDto.getFirstName(),
                signUpDto.getLastName(),
                signUpDto.getEmail(),
                encryptedpassword
        );
        userRepository.save(createUser);

        final AuthenticationTokens authenticationTokens = new AuthenticationTokens(createUser);
        authenticationTokenService.saveToken(authenticationTokens);

        ResponseDto responseDto = new ResponseDto("success", "user created successfully");
        return responseDto;
    }

    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String hash = DatatypeConverter
                .printHexBinary(digest).toUpperCase();
        return hash;
    }

    public SigninResponseDto signIn(SignInDto signInDto) throws NoSuchAlgorithmException {
        User user = userRepository.findByEmail(signInDto.getEmail());
        if(Objects.isNull(user)){
            throw new NotFoundException("Not found user");
        }
        try{
            if(!user.getPassword().equals(hashPassword(signInDto.getPassword()))){
                throw new AuthException("wrong password");
            }
        }
        catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        AuthenticationTokens token = authenticationTokenService.getToken(user);
        if(Objects.isNull(token)){
            throw new NotFoundException("token is not present");
        }
        return new SigninResponseDto("success", token.getToken());
    }

    @Transactional
    public ResponseDto deleteUser(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isEmpty()){
            throw new NotFoundException("User Not Found");
        }

//        userRepo.deleteTokenByUser(optionalUser.get().getId());

        userRepository.deleteById(optionalUser.get().getId());
        return new ResponseDto("OK", "Deleted successfully");
    }
}
