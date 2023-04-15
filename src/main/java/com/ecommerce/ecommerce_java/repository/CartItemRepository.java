package com.ecommerce.ecommerce_java.repository;

import com.ecommerce.ecommerce_java.model.Carts;
import com.ecommerce.ecommerce_java.model.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItems, Integer> {
    List<CartItems> findByCart(Carts carts);
}
