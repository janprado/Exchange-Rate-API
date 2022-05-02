package com.janprado.exchangerateapi.service;

import com.janprado.exchangerateapi.model.ConvertResponse;
import com.janprado.exchangerateapi.model.RateResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
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
        log.info("fetch exchange rate from currency [{}] to currency [{}].", currencyA, currencyB);
        return webClient
                .get()
                .uri("/latest?base=" + currencyA + "&symbols=" + currencyB + "&places=4")
                .accept(APPLICATION_JSON) // verificar accept
                .retrieve() // verificar o que faz
                .onStatus(HttpStatus::is4xxClientError, //Verificar o que este erro retorna
                        error -> Mono.error(new RuntimeException("error, verify currencys "))) //entender melhor o arrow function o que faz
                .bodyToMono(RateResponse.class);
    }

    public Mono<RateResponse> findAllRatesFromCurrency (String currency){
        log.info("fetch all exchange rates from currency [{}].", currency);
        return webClient
                .get()
                .uri("/latest?base=" + currency + "&places=4")
                .accept(APPLICATION_JSON) // verificar accept
                .retrieve() // verificar o que faz
                .onStatus(HttpStatus::is4xxClientError, //Verificar o que este erro retorna
                        error -> Mono.error(new RuntimeException("error, verify currency "))) //entender melhor o arrow function o que faz
                .bodyToMono(RateResponse.class);

    }

    public Flux<ConvertResponse> findValueConversionFromCurrencyAtoB(String currencyA, String currencyB, Number amount) {
        log.info("Convert amount [{}] from currency [{}] to currency [{}].", amount, currencyA, currencyB);
        return webClient
                .get()
                .uri("/convert?from=" + currencyA + "&to=" + currencyB + "&amount=" + amount + "&places=2")
                .accept(APPLICATION_JSON) // verificar accept
                .retrieve() // verificar o que faz
                .onStatus(HttpStatus::is4xxClientError, //Verificar o que este erro retorna
                        error -> Mono.error(new RuntimeException("error, verify currencys and amount ")))
                .bodyToFlux(ConvertResponse.class);
    }

}
