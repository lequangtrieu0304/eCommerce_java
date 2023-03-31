package com.ecommerce.ecommerce_java.controller;

import com.ecommerce.ecommerce_java.dto.ResponseDto;
import com.ecommerce.ecommerce_java.dto.user.SignInDto;
import com.ecommerce.ecommerce_java.dto.user.SigninResponseDto;
import com.ecommerce.ecommerce_java.dto.user.SignUpDto;
import com.ecommerce.ecommerce_java.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/signup")
    public ResponseDto signup(@RequestBody SignUpDto signUpDto) throws NoSuchAlgorithmException {
        return userService.signUp(signUpDto);
    }

    @PostMapping("/signin")
    public SigninResponseDto signin(@RequestBody SignInDto signInDto) throws NoSuchAlgorithmException {
        return userService.signIn(signInDto);
    }
    @DeleteMapping("/{id}")
    public ResponseDto deleteUser(@PathVariable("id") Integer id){
        return userService.deleteUser(id);
    }
}
