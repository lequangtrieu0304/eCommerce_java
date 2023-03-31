package com.ecommerce.ecommerce_java.repository;

import com.ecommerce.ecommerce_java.model.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItem extends JpaRepository<CartItems, Integer> {
}
