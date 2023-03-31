package com.ecommerce.ecommerce_java.repository;

import com.ecommerce.ecommerce_java.model.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishListRepo extends JpaRepository<WishList, Integer> {
    @Modifying
    @Query(value = "select concat(u.first_name, ' ', u.last_name) as fullname, w.user_id, count(w.user_id) as wishlist_count from wishlist w join users u on u.id=w.user_id group by w.user_id order \n" +
            "by wishlist_count desc limit 1",
            nativeQuery = true)
    List<Object[]> userHaveWishList();
}
