package com.ecommerce.ecommerce_java.repository;

import com.ecommerce.ecommerce_java.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepo extends JpaRepository<Review, Integer> {
}
