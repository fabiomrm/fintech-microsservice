package com.fmrm.card.service.infra.repository;

import com.fmrm.card.service.domain.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {
    @Query(nativeQuery = true,value = "SELECT * FROM card WHERE card.income <= :income")
    List<Card> findByIncomeLessThanEqual(@Param("income") BigDecimal income);

}
