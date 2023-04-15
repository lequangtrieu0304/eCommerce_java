package com.ecommerce.ecommerce_java.repository;

import com.ecommerce.ecommerce_java.model.Product;
import com.ecommerce.ecommerce_java.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findByProduct(Product product);
}
