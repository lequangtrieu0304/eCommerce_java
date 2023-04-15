package com.ecommerce.ecommerce_java.repository;

import com.ecommerce.ecommerce_java.model.Carts;
import com.ecommerce.ecommerce_java.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Carts, Integer> {
//    @Query(value = "select * from carts c where c.user_id = :id", nativeQuery = true)
//    Optional<Cart> findByUser(@Param("id") Integer id);
    Carts findByUser(User user);
}
