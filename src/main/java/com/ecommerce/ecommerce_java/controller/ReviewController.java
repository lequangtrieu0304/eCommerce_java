package com.ecommerce.ecommerce_java.controller;

import com.ecommerce.ecommerce_java.common.ApiResponse;
import com.ecommerce.ecommerce_java.dto.review.ReviewDto;
import com.ecommerce.ecommerce_java.model.User;
import com.ecommerce.ecommerce_java.service.AuthenticationTokenService;
import com.ecommerce.ecommerce_java.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/review")
public class ReviewController {
    @Autowired
    ReviewService reviewService;
    @Autowired
    AuthenticationTokenService authenticationTokenService;
    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createReview(@RequestBody ReviewDto reviewDto,
                                                    @RequestParam("token") String token){
        authenticationTokenService.authenticate(token);
        User user = authenticationTokenService.getUser(token);
        reviewService.createReview(user, reviewDto.getProduct_id(), reviewDto.getComment(), reviewDto.getRating());
        return new ResponseEntity<>(new ApiResponse(true, "Added review"), HttpStatus.CREATED);
    }
}
