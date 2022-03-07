package com.example.demo.logic;

import com.example.demo.dao.entity.CoinDeskEntity;
import com.example.demo.dto.CoinDeskRequestDTO;
import com.example.demo.dto.TransformBpiDTO;

import java.io.IOException;

public interface CoinDesk {

    TransformBpiDTO transform() throws IOException;
    CoinDeskEntity find(String currencyCode);
    String createOrUpdate(CoinDeskRequestDTO model);
    String delete(CoinDeskRequestDTO model);

}
