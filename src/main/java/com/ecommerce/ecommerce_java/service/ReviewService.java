package com.ecommerce.ecommerce_java.service;

import com.ecommerce.ecommerce_java.exceptions.CustomException;
import com.ecommerce.ecommerce_java.exceptions.NotFoundException;
import com.ecommerce.ecommerce_java.model.Product;
import com.ecommerce.ecommerce_java.model.Review;;
import com.ecommerce.ecommerce_java.model.User;
import com.ecommerce.ecommerce_java.repository.ProductRepository;
import com.ecommerce.ecommerce_java.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReviewService {
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    ProductRepository productRepository;
    HashMap<Integer, Set<Integer>> productReviewMap = new HashMap<>();
    public void createReview(User user, Integer productId, String comment, int rating) {
        Product product = productRepository.findById(productId).orElseThrow(() ->
                new NotFoundException("Not found product"));
        List<Review> reviews = reviewRepository.findByProduct(product);
        for(Review review : reviews){
            if(productReviewMap.containsKey(product.getId())){
                Set<Integer> userIdSet = productReviewMap.get(product.getId());
                userIdSet.add(review.getUser().getId());
                productReviewMap.put(product.getId(), userIdSet);
            }
            else{
                Set<Integer> userIdSet = new HashSet<>();
                userIdSet.add(review.getUser().getId());
                productReviewMap.put(product.getId(), userIdSet);
            }
        }
        if(productReviewMap.containsKey(product.getId())){
            Set<Integer> userIdSet = productReviewMap.get(product.getId());
            if(userIdSet.contains(user.getId())){
                throw new CustomException("San pham da duoc danh gia");
            }
            else {
                Review review = new Review();
                review.setUser(user);
                review.setProduct(product);
                review.setRating(rating);
                review.setComment(comment);
                reviewRepository.save(review);
                reviews.add(review);
                int numReviews = reviews.size();
                int subTotalReview = reviews
                                    .stream()
                                    .reduce(0, (subTotal, r) ->
                                                    subTotal + r.getRating(),
                                                    Integer::sum);
                double productRating = Math.round((double) subTotalReview/numReviews * 10.0)/10.0;
                product.setRating(productRating);
                product.setNumberReviews(numReviews);
                productRepository.save(product);
            }
        }
        else {
            Review review = new Review();
            review.setUser(user);
            review.setProduct(product);
            review.setRating(rating);
            review.setComment(comment);
            reviewRepository.save(review);

            product.setRating(rating);
            product.setNumberReviews(1);
            productRepository.save(product);
        }
    }
}