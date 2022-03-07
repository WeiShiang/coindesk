package com.example.demo.controller;

import com.example.demo.dao.entity.CoinDeskEntity;
import com.example.demo.dto.CoinDeskDTO;
import com.example.demo.dto.CoinDeskRequestDTO;
import com.example.demo.dto.TransformBpiDTO;
import com.example.demo.logic.CoinDesk;
import com.example.demo.logic.impl.CoinDeskImpl;
import com.example.demo.util.HttpUtil;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashMap;

@RestController
public class CoinDeskController {
    private static Logger log = LogManager.getLogger(CoinDeskController.class);

    @Autowired
    private CoinDesk coinDesk;

    /**
     * call coindesk API
     *
     * @return
     */
    @GetMapping(value = {"/bpi/currentprice"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public CoinDeskDTO getBPIPrice() {
        String response = "";
        CoinDeskDTO rtnObj = null;
        try {
            HashMap headers = new HashMap<String, String>();
            headers.put("Content-Type", "application/json; charset=utf-8");
            String apiResponse = HttpUtil.doGet(CoinDeskImpl.COIN_DESK_API, headers);
            log.debug("responseBody={}", apiResponse);
            rtnObj = new Gson().fromJson(apiResponse, CoinDeskDTO.class);

        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return rtnObj;
    }

    /**
     * transform coindesk API
     * localhost:8080/example/bpi/transformation/price
     *
     * @return
     */
    @GetMapping(value = {"/bpi/transformation/price"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public TransformBpiDTO transformBPIPrice() {
        TransformBpiDTO rtnObj = null;
        try {
            rtnObj = coinDesk.transform();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return rtnObj;
    }

    /**
     * find
     * localhost:8080/example/bpi/{currencyCode}/price
     *
     * @return
     */
    @GetMapping(value = {"/bpi/{currencyCode}/price"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public CoinDeskEntity find(@PathVariable(value = "currencyCode") @NotBlank @Size(max=3) String currencyCode) {
        CoinDeskEntity rtnObj = null;
        try {
            log.debug("currencyCode={}", currencyCode);
            rtnObj = coinDesk.find(currencyCode);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return rtnObj;
    }

    @PostMapping(value = {"/bpi/addprice"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity create( @RequestBody @Valid CoinDeskRequestDTO model) {
        CoinDeskEntity rtnObj = null;
        String response = "";
        try {
            response =  coinDesk.createOrUpdate(model);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @PostMapping(value = {"/bpi/updateprice"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity update( @RequestBody CoinDeskRequestDTO model) {
        CoinDeskEntity rtnObj = null;
        String response = "";
        try {
            response = coinDesk.createOrUpdate(model);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @DeleteMapping(value = {"/bpi/price"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity delete(@RequestBody CoinDeskRequestDTO model) {
        CoinDeskEntity delete = null;
        String response = "";
        try {
            response = coinDesk.delete(model);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }
}
