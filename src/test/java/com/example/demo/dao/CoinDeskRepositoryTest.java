package com.example.demo.dao;

import com.example.demo.dao.entity.CoinDeskEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CoinDeskRepositoryTest {

    @Autowired
    private CoinDeskRepository repository;

    @Test
    public void testSave() {
        CoinDeskEntity coinDeskEntity = new CoinDeskEntity();
        coinDeskEntity.setCurrencyCode("222");
        coinDeskEntity.setCurrencyName("111");
        coinDeskEntity.setRateFloat(BigDecimal.valueOf(10.1111));
        coinDeskEntity.setUpdatedTimeGmt("2020-01-01");
        coinDeskEntity.setUpdatedTimeUtc("2020-01-01");
        coinDeskEntity.setUpdatedTimeIso("2020-01-01");
        CoinDeskEntity rtnObj = repository.save(coinDeskEntity);
        assertThat(rtnObj).isNotNull();
    }

    @Test
    public void testFindByCurrencyCode() {
        CoinDeskEntity coinDeskEntity = repository.findByCurrencyCode("GBP");
        assertThat(coinDeskEntity).isNotNull();
    }

    @Test
    public void testUpdate() {
        CoinDeskEntity coinDeskEntity = new CoinDeskEntity();
        coinDeskEntity.setCurrencyCode("222");
        coinDeskEntity.setCurrencyName("33333333333");
        coinDeskEntity.setRateFloat(BigDecimal.valueOf(10.1111));
        coinDeskEntity.setUpdatedTimeGmt("2020-01-01");
        coinDeskEntity.setUpdatedTimeUtc("2020-01-01");
        coinDeskEntity.setUpdatedTimeIso("2020-01-01");
        CoinDeskEntity rtnObj = repository.save(coinDeskEntity);
        assertThat(rtnObj).isNotNull();
    }

    @Test
    public void testDelete() {
        CoinDeskEntity coinDeskEntity = new CoinDeskEntity();
        coinDeskEntity.setCurrencyCode("222");

        repository.delete(coinDeskEntity);
        CoinDeskEntity rtnObj = repository.findByCurrencyCode("222");
        assertThat(rtnObj).isNull();
    }

}
