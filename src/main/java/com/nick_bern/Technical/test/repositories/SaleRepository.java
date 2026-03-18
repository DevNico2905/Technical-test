package com.nick_bern.Technical.test.repositories;

import com.nick_bern.Technical.test.models.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Long> {
}
