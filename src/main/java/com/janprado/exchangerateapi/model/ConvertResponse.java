package com.janprado.exchangerateapi.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import java.time.LocalDate;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ConvertResponse {

    private ListConverterResponse query;
    private LocalDate date;
    private Double result;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getResult() {
        return result;
    }

    public void setResult(Double result) {
        this.result = result;
    }

    public ListConverterResponse getQuery() {
        return query;
    }

    public void setQuery(ListConverterResponse query) {
        this.query = query;
    }
}
