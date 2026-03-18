package com.nick_bern.Technical.test.repositories;

import com.nick_bern.Technical.test.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
