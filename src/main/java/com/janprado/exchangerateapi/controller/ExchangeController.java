package com.janprado.exchangerateapi.controller;

import com.janprado.exchangerateapi.model.AvailableCurrenciesResponse;
import com.janprado.exchangerateapi.model.ConvertResponse;
import com.janprado.exchangerateapi.model.RateResponse;
import com.janprado.exchangerateapi.service.ExchangeRateService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Api(value = "Exchange Rate API", tags = "API Endpoints")
@RestController
@RequestMapping("/api")
public class ExchangeController {

    ExchangeRateService exchangeRateService;

    @Autowired
    public ExchangeController(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    @Cacheable(value = "cacheCurrencies", unless = "#result==null")
    @ApiOperation(value = "Return exchange rate from currency A to currency B")
    @GetMapping(value = "/exchangeRateFromCurrencyAToB")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Error, verify input and output currency")
    })
    public Mono<RateResponse> getExchangeRateFromCurrencyAToB(
            @RequestParam(value = "currencyA") @ApiParam(value = "Enter the three-letter currency code you would like", required = true) String currencyA,
            @RequestParam(value = "currencyB") @ApiParam(value = "Enter the three-letter currency code which currency you want the exchange rate", required = true) String currencyB) {

        return exchangeRateService.findExchangeRateFromCurrencyAToB(currencyA, currencyB);
    }

    @Cacheable(value = "cacheCurrencies", unless = "#result==null")
    @ApiOperation(value = "Return all exchange rates from the selected currency")
    @GetMapping(value = "/allExchangeRatesFromCurrency")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Error, check selected currency")
    })
    public Mono<RateResponse> getAllExchangeRatesFromCurrency(
            @RequestParam(value = "currency") @ApiParam(value = "Enter the three-letter currency code you would like to get all exchange rates", required = true) String currency) {

        return exchangeRateService.findAllRatesFromCurrency(currency);
    }

    @Cacheable(value = "cacheCurrencies", unless = "#result==null")
    @ApiOperation(value = "Return the value of currency A converted to currency B")
    @GetMapping(value = "/valueConversionFromCurrencyAToB")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Error, check selected currency and amount")
    })
    public Mono<ConvertResponse> getValueConversionFromCurrencyAToB(
            @RequestParam(value = "currencyA") @ApiParam(value = "Enter the three-letter currency code you would like to convert from", required = true) String currencyA,
            @RequestParam(value = "currencyB") @ApiParam(value = "Enter the three-letter currency code you would like to convert to", required = true) String currencyB,
            @RequestParam(value = "amount") @ApiParam(value = "Enter the amount you would like to be converted") Number amount) {

        return exchangeRateService.findValueConversionFromCurrencyAtoB(currencyA, currencyB, amount);
    }

    @Cacheable(value = "cacheCurrencies", unless = "#result==null")
    @ApiOperation(value = "Return the value of currency A converted to currency List")
    @GetMapping(value = "/valueConversionFromCurrencyAToCurrencyList")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Error, check selected currencies and value")
    })
    public Mono<RateResponse> getValueConversionFromCurrencyAtoCurrencyList(
            @RequestParam(value = "currencyA") @ApiParam(value = "Enter the three-letter currency code you would like to convert", required = true) String currencyA,
            @RequestParam(value = "currencyList") @ApiParam(value = "Enter the three-letter currency code of the currencies you would like to convert, separating them with a comma", required = true) String currencyList,
            @RequestParam(value = "amount") @ApiParam(value = "Enter the amount you would like to be converted") Number amount) {

        return exchangeRateService.findValueConversionFromCurrencyAtoCurrencyList(currencyA, currencyList, amount);
    }

    @Cacheable(value = "cacheCurrencies", unless = "#result==null")
    @ApiOperation(value = "Return all available currencies and description")
    @GetMapping(value = "/availableCurrencies")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Error")
    })
    public Mono<AvailableCurrenciesResponse> getAvailableCurrency() {

        return exchangeRateService.findAvailableCurrencies();
    }

}
