package com.ecommerce.ecommerce_java.repository;

import com.ecommerce.ecommerce_java.model.Categorys;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatetoryRepository extends JpaRepository<Categorys, Integer> {
}
