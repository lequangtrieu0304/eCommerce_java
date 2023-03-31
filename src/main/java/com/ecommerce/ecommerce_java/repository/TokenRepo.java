package com.ecommerce.ecommerce_java.repository;

import com.ecommerce.ecommerce_java.model.AuthenticationToken;
import com.ecommerce.ecommerce_java.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepo extends JpaRepository<AuthenticationToken, Integer> {
    AuthenticationToken findByUser(User user);

    AuthenticationToken findByToken(String token);
}
