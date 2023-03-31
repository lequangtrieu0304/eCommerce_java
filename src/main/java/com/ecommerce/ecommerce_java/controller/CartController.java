package com.ecommerce.ecommerce_java.controller;

import com.ecommerce.ecommerce_java.common.ApiResponse;
import com.ecommerce.ecommerce_java.dto.cart.AddToCartDto;
import com.ecommerce.ecommerce_java.exceptions.NotFoundException;
import com.ecommerce.ecommerce_java.model.Product;
import com.ecommerce.ecommerce_java.model.User;
import com.ecommerce.ecommerce_java.service.AuthenticationTokenService;
import com.ecommerce.ecommerce_java.service.CartService;
import com.ecommerce.ecommerce_java.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    CartService cartService;
    @Autowired
    AuthenticationTokenService authenticationTokenService;
    @Autowired
    ProductService productService;
    @PostMapping("/createCart")
    public ResponseEntity<ApiResponse> createCart(@RequestBody AddToCartDto addToCartDto,
                                                  @RequestParam("token") String token) throws NotFoundException{
        authenticationTokenService.authenticate(token);
        User user = authenticationTokenService.getUser(token);
        cartService.addToCart(addToCartDto, user);
        return new ResponseEntity<>(new ApiResponse(true, "ADDED successully"), HttpStatus.CREATED);
    }
}
