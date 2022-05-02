package com.janprado.exchangerateapi.controller;

import com.janprado.exchangerateapi.model.ConvertResponse;
import com.janprado.exchangerateapi.model.ConvertToSelectionResponse;
import com.janprado.exchangerateapi.model.RateResponse;
import com.janprado.exchangerateapi.service.ExchangeRateService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
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

    @ApiOperation(value = "Return exchange rate from currency A to currency B")
    @GetMapping(value = "/exchangeRateFromCurrencyAToB")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Error, verify input and output currency")
    })
    public Mono<RateResponse> getExchangeRateFromCurrencyAToB(@RequestParam(value = "currencyA") @ApiParam("Enter the currency to be converted") String currencyA,
                                                              @RequestParam(value = "currencyB") @ApiParam("Enter which currency you want to convert to") String currencyB) {
        return exchangeRateService.findExchangeRateFromCurrencyAToB(currencyA, currencyB);
    }

    @ApiOperation(value = "Return all exchange rates from the selected currency")
    @GetMapping(value = "/allExchangeRatesFromCurrency")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Error, check selected currency")
    })
    public Mono<RateResponse> getAllExchangeRatesFromCurrency(@RequestParam(value = "currency") @ApiParam("Enter the three-letter currency code you would like to get all exchange rates") String currency) {

        return exchangeRateService.findAllRatesFromCurrency(currency);
    }

    @ApiOperation(value = "Return the value of currency A converted to currency B")
    @GetMapping(value = "/valueConversionFromCurrencyAToB")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Error, check selected currency and amount")
    })
    public Mono<ConvertResponse> getValueConversionFromCurrencyAToB(@RequestParam(value = "currencyA") @ApiParam("Enter the three-letter currency code you would like to convert from") String currencyA,
                                                                    @RequestParam(value = "currencyB") @ApiParam("Enter the three-letter currency code you would like to convert to") String currencyB,
                                                                    @RequestParam(value = "amount") @ApiParam("Enter the amount you would like to be converted") Number amount) {
        return exchangeRateService.findValueConversionFromCurrencyAtoB(currencyA, currencyB, amount);
    }

    @ApiOperation(value = "Return the value of currency A converted to currency List")
    @GetMapping(value = "/valueConversionFromCurrencyAToCurrencyList")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Error, check selected currencies and value")
    })
    public Mono<RateResponse> getValueConversionFromCurrencyAtoCurrencyList(@RequestParam(value = "currencyA") @ApiParam("Enter the three-letter currency code you would like to convert from") String currencyA,
                                                                            @RequestParam(value = "currencyList") @ApiParam("Enter the three-letter currency code of the currencies you would like to convert, separating them with a comma") String currencyList,
                                                                            @RequestParam(value = "amount") @ApiParam("Enter the amount you would like to be converted") Number amount) {
        return exchangeRateService.findValueConversionFromCurrencyAtoCurrencyList(currencyA, currencyList, amount);
    }

}
