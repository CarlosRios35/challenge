package com.between.challenge.infrastructure.database.repository;

import com.between.challenge.domain.model.Prices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface PricesRepository extends JpaRepository<Prices, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM prices WHERE company_id=:companyId AND product_id=:productId AND :date between start_date AND end_date order by priority desc limit 1")
    Optional<Prices> findByBrandIdAndProductIdAndDateAndHigherPriority(Integer companyId, Integer productId, Date date);
}


