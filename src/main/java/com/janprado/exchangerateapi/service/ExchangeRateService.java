package com.janprado.exchangerateapi.service;

import com.janprado.exchangerateapi.model.RateResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    public Mono<RateResponse> findAllRatesByACoin (String base){
        log.info("fetch all rates from base currency [{}].", base);
        return webClient
                .get()
                .uri("/latest?base=" + base)
                .accept(APPLICATION_JSON) // verificar accept
                .retrieve() // verificar o que faz
                .onStatus(HttpStatus::is4xxClientError, //Verificar o que este erro retorna
                        error -> Mono.error(new RuntimeException("verify base current "))) //entender melhor o arrow function o que faz
                .bodyToMono(RateResponse.class);

    }


}