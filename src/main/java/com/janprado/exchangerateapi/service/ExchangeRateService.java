package com.janprado.exchangerateapi.service;

import com.janprado.exchangerateapi.model.AvailableCurrenciesResponse;
import com.janprado.exchangerateapi.model.ConvertResponse;
import com.janprado.exchangerateapi.model.RateResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Service
public class ExchangeRateService {

    private static final Logger log = LoggerFactory.getLogger(ExchangeRateService.class);
    private final WebClient webClient;

    public ExchangeRateService(WebClient.Builder builder) {
        webClient = builder.baseUrl("https://api.exchangerate.host").build();
    }

    public Mono<RateResponse> findExchangeRateFromCurrencyAToB(String currencyA, String currencyB) {
        log.info("Fetch exchange rate from currency [{}] to currency [{}].", currencyA, currencyB);
        return webClient
                .get()
                .uri("/latest?base=" + currencyA + "&symbols=" + currencyB + "&places=4")
                .accept(APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        error -> Mono.error(new RuntimeException("error, verify currencies ")))
                .bodyToMono(RateResponse.class);
    }

    public Mono<RateResponse> findAllRatesFromCurrency(String currency) {
        log.info("Fetch all exchange rates from currency [{}].", currency);
        return webClient
                .get()
                .uri("/latest?base=" + currency + "&places=4")
                .accept(APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        error -> Mono.error(new RuntimeException("error, verify currency ")))
                .bodyToMono(RateResponse.class);
    }

    public Mono<ConvertResponse> findValueConversionFromCurrencyAtoB(String currencyA, String currencyB, Number amount) {
        log.info("Convert amount [{}] from currency [{}] to currency [{}].", amount, currencyA, currencyB);
        return webClient
                .get()
                .uri("/convert?from=" + currencyA + "&to=" + currencyB + "&amount=" + amount + "&places=2")
                .accept(APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        error -> Mono.error(new RuntimeException("error, verify currencies and amount ")))
                .bodyToMono(ConvertResponse.class);
    }

    public Mono<RateResponse> findValueConversionFromCurrencyAtoCurrencyList(String currencyA, String currencyList, Number amount) {
        log.info("Convert amount [{}] from currency [{}] to currency List [{}].", amount, currencyA, currencyList);
        return webClient
                .get()
                .uri("/latest?base=" + currencyA + "&symbols=" + currencyList + "&amount=" + amount + "&places=2")
                .accept(APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        error -> Mono.error(new RuntimeException("error, verify currencies.")))
                .bodyToMono(RateResponse.class);
    }

    public Mono<AvailableCurrenciesResponse> findAvailableCurrencies() {
        log.info("Fetch all available currencies.");
        return webClient
                .get()
                .uri("/symbols")
                .accept(APPLICATION_JSON)
                .retrieve()
                .bodyToMono(AvailableCurrenciesResponse.class);
    }
}
