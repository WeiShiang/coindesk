package com.example.demo.dao;

import com.example.demo.dao.entity.CoinDeskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoinDeskRepository extends JpaRepository<CoinDeskEntity, String> {
    CoinDeskEntity findByCurrencyCode(String currencyCode);
}
