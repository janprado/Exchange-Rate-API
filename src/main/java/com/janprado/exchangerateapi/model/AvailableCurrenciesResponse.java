package com.janprado.exchangerateapi.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.JsonNode;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class AvailableCurrenciesResponse {

    private JsonNode symbols;

    public JsonNode getSymbols() {
        return symbols;
    }

    public void setSymbols(JsonNode symbols) {
        this.symbols = symbols;
    }
}
