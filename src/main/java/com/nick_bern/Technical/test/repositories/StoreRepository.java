package com.nick_bern.Technical.test.repositories;

import com.nick_bern.Technical.test.models.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
