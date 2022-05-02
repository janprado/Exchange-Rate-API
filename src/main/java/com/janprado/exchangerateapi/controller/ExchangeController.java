package com.janprado.exchangerateapi.controller;

import com.janprado.exchangerateapi.model.RateResponse;
import com.janprado.exchangerateapi.service.ExchangeRateService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Api(value = "Exchange Rate API", tags = "API Endpoints")
@RestController
@RequestMapping("/webclient")
public class ExchangeController {

    ExchangeRateService exchangeRateService;

    @Autowired
    public ExchangeController(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    @ApiOperation(value="return exchange rate from Currency A to Currency B") //melhorar descricao
    @GetMapping(value = "/exchangeRateFromCurrencyAToB")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Error, verify input and output currency")
    })
    public Mono<RateResponse> getExchangeRateFromCurrencyAToB(@RequestParam(value = "CurrencyA ") @ApiParam("Enter the currency to be converted") String currencyA,
                                                              @RequestParam(value = "CurrencyB ") @ApiParam("Enter which currency you want to convert to")String currencyB)
    {
        return exchangeRateService.findExchangeRateFromCurrencyAToB(currencyA, currencyB);
    }

    @ApiOperation(value="Return all exchange rates from the selected currency")
    @GetMapping(value = "/allExchangeRatesFromCurrency")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Error, check selected currency")
    })
    public Mono<RateResponse> getAllExchangeRatesFromCurrency(@RequestParam(value = "Currency") @ApiParam("Enter the currency you want to get all exchange rates") String currency){

        // NAO ACEITAR PARAMETRO VAZIO, RETORNAR ERRO
        return exchangeRateService.findAllRatesByACurrency(currency);
    }



}
