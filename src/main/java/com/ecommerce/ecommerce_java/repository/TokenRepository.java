package com.ecommerce.ecommerce_java.repository;

import com.ecommerce.ecommerce_java.model.AuthenticationTokens;
import com.ecommerce.ecommerce_java.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<AuthenticationTokens, Integer> {
    AuthenticationTokens findByUser(User user);

    AuthenticationTokens findByToken(String token);
}
