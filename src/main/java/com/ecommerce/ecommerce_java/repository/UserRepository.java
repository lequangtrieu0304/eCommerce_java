package com.ecommerce.ecommerce_java.repository;

import com.ecommerce.ecommerce_java.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
//    @Modifying
//    @Query("DELETE FROM AuthenticationToken t WHERE t.user.id = :userId")
//    void deleteTokenByUser(@Param("userId") Integer userId);
}
