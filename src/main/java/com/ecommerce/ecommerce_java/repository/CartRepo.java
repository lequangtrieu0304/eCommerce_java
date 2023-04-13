package com.ecommerce.ecommerce_java.repository;

import com.ecommerce.ecommerce_java.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepo extends JpaRepository<Cart, Integer> {
//    @Query(value = "select * from carts c where c.user_id = :id", nativeQuery = true)
//    Optional<Cart> findByUser(@Param("id") Integer id);
    Cart findByUser(Integer id);
}
