package com.janprado.exchangerateapi.model;

import java.time.LocalDate;
import java.util.Map;

public class ConvertToSelectionResponse {

    private boolean success;
    private String base;
    private LocalDate date;
    private Map<String, Double> rates;
    /*private Number amount;*/

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Map<String, Double> getRates() {
        return rates;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }

    /*public Number getAmount() {
        return amount;
    }

    public void setAmount(Number amount) {
        this.amount = amount;
    }*/
}
