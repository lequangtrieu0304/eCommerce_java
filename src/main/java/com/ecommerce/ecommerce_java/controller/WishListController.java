package com.ecommerce.ecommerce_java.controller;

import com.ecommerce.ecommerce_java.common.ApiResponse;
import com.ecommerce.ecommerce_java.exceptions.AuthFailException;
import com.ecommerce.ecommerce_java.model.Product;
import com.ecommerce.ecommerce_java.model.User;
import com.ecommerce.ecommerce_java.model.WishList;
import com.ecommerce.ecommerce_java.service.AuthenticationTokenService;
import com.ecommerce.ecommerce_java.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/wishlist")
public class WishListController {
    @Autowired
    WishListService wishListService;
    @Autowired
    AuthenticationTokenService authenticationTokenService;
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addToWishList(@RequestBody Product product,
                                                     @RequestParam("token") String token){
        //authenticate token
        authenticationTokenService.authenticate(token);
        User user = authenticationTokenService.getUser(token);
        WishList wishList = new WishList(user, product);
        wishListService.createWishList(wishList);
        return new ResponseEntity<>(new ApiResponse(true, "Added to wishlist"), HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteWishList(@PathVariable("id") Integer id,
                                                       @RequestParam("token") String token){
        authenticationTokenService.authenticate(token);
        User user = authenticationTokenService.getUser(token);
        if(Objects.isNull(user)){
            throw new AuthFailException("Ban chua login");
        }
        wishListService.deleteWishList(id);
        return new ResponseEntity<>(new ApiResponse(true, "Deleted successully"), HttpStatus.OK);
    }
    @GetMapping("/userHaveWishList")
    public ResponseEntity<List<Object[]>> userHaveWishList(){
        List<Object[]> result = wishListService.userHaveWishList();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
