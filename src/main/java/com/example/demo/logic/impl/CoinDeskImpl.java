package com.example.demo.logic.impl;

import com.example.demo.dao.CoinDeskRepository;
import com.example.demo.dao.entity.CoinDeskEntity;
import com.example.demo.dto.*;
import com.example.demo.enums.Currency;
import com.example.demo.enums.ResponseStatus;
import com.example.demo.logic.CoinDesk;
import com.example.demo.util.DateUtil;
import com.example.demo.util.HttpUtil;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Objects;


@Service
public class CoinDeskImpl implements CoinDesk {
    private static Logger log = LogManager.getLogger(CoinDeskImpl.class);
    public final static String COIN_DESK_API = "https://api.coindesk.com/v1/bpi/currentprice.json";

    @Autowired
    private CoinDeskRepository repository;

    @Override
    public TransformBpiDTO transform() throws IOException {
        HashMap headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json; charset=utf-8");
        String apiResponse = HttpUtil.doGet(COIN_DESK_API, headers);
        log.debug("responseBody={}", apiResponse);
        CoinDeskDTO coinDeskDTO = new Gson().fromJson(apiResponse, CoinDeskDTO.class);
        TransformBpiDTO rtnObj = new TransformBpiDTO();

        //更新時間
        rtnObj.setUpdatedTime(DateUtil.getInstance().formatDate(coinDeskDTO.getTime().getUpdatedISO()));

        //幣別相關資訊
        USD usdMole = new USD(coinDeskDTO.getBpi().getUSD().getCode(),
                Currency.USD.getValue(), "",
                coinDeskDTO.getBpi().getUSD().getRateFloat(), "");
        rtnObj.setUSD(usdMole);

        GBP gbpMole = new GBP(coinDeskDTO.getBpi().getGBP().getCode(),
                Currency.GBP.getValue(), "",
                coinDeskDTO.getBpi().getGBP().getRateFloat(), "");
        rtnObj.setGBP(gbpMole);

        EUR eruMole = new EUR(coinDeskDTO.getBpi().getEUR().getCode(),
                Currency.EUR.getValue(), "",
                coinDeskDTO.getBpi().getEUR().getRateFloat(), "");
        rtnObj.setEUR(eruMole);
        return rtnObj;
    }

    @Override
    public CoinDeskEntity find(String currencyCode) {
        return repository.findByCurrencyCode(currencyCode);
    }

    @Override
    public String createOrUpdate(CoinDeskRequestDTO model) {
        CoinDeskEntity coinDeskEntity = new CoinDeskEntity();

        coinDeskEntity.setCurrencyCode(model.getCurrencyCode());
        coinDeskEntity.setCurrencyName(model.getCurrencyName());
        coinDeskEntity.setRateFloat(model.getRateFloat());
        coinDeskEntity.setUpdatedTimeGmt(model.getUpdatedTimeGmt());
        coinDeskEntity.setUpdatedTimeUtc(model.getUpdatedTimeUtc());
        coinDeskEntity.setUpdatedTimeIso(model.getUpdatedTimeIso());
        CoinDeskEntity rtnObj = repository.save(coinDeskEntity);
        log.debug(rtnObj.toString());

        String message  = ResponseStatus.SUCCESSFUL.getReasonPhrase();
        String statusCode  = String.valueOf(ResponseStatus.SUCCESSFUL.value());
        if ( Objects.isNull(rtnObj)) {
            message =  ResponseStatus.FAIL.getReasonPhrase();
            statusCode  = String.valueOf(ResponseStatus.FAIL.value());
        }
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse.ApiErrorResponseBuilder()
                                                .withMessage(message)
                                                .withError_code(statusCode)
                                                .build();
        return new Gson().toJson(apiErrorResponse.toString());
    }

    @Override
    public String delete(CoinDeskRequestDTO model) {
        CoinDeskEntity coinDeskEntity = new CoinDeskEntity();

        coinDeskEntity.setCurrencyCode(model.getCurrencyCode());
        coinDeskEntity.setCurrencyName(model.getCurrencyName());
        coinDeskEntity.setRateFloat(model.getRateFloat());
        coinDeskEntity.setUpdatedTimeGmt(model.getUpdatedTimeGmt());
        coinDeskEntity.setUpdatedTimeUtc(model.getUpdatedTimeUtc());
        coinDeskEntity.setUpdatedTimeIso(model.getUpdatedTimeIso());
        repository.delete(coinDeskEntity);

        String message  = ResponseStatus.SUCCESSFUL.getReasonPhrase();
        String statusCode  = String.valueOf(ResponseStatus.SUCCESSFUL.value());
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse.ApiErrorResponseBuilder()
                                                .withMessage(message)
                                                .withError_code(statusCode)
                                                .build();
        return new Gson().toJson(apiErrorResponse.toString());
    }
}
