package com.janprado.exchangerateapi.controller;

import com.janprado.exchangerateapi.model.RateResponse;
import com.janprado.exchangerateapi.service.ExchangeRateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Api(value = "Exchange Rate API")
@RestController
@RequestMapping("/webclient")
public class ExchangeController {

    ExchangeRateService exchangeRateService;

    @Autowired
    public ExchangeController(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    @ApiOperation(value="return exchange rate base compar to all") //melhorar descricao
    @RequestMapping("/rate")
    public Mono<RateResponse> getAllRatesByBase(@RequestParam(value = "coin") String coin){

        // NAO ACEITAR PARAMETRO VAZIO, RETORNAR ERRO
        return exchangeRateService.findAllRatesByACoin(coin);
    }



}
