package com.ecommerce.ecommerce_java.repository;

import com.ecommerce.ecommerce_java.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatetoryRepo extends JpaRepository<Category, Integer> {
}
