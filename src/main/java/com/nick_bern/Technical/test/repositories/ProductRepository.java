package com.nick_bern.Technical.test.repositories;

import com.nick_bern.Technical.test.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findProductByProductName(String productName);
}
