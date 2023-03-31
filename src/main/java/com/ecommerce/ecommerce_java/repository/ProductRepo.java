package com.ecommerce.ecommerce_java.repository;

import com.ecommerce.ecommerce_java.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {

}
