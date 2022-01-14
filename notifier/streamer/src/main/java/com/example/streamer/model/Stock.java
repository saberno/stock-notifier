package com.example.streamer.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Stock {

    private int id;
    private String symbol;
    private LocalDateTime createdDate;
    private BigDecimal stockValue;

    public int getId() {
        return id;
    }

    public String getSymbol() {
        return symbol;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public BigDecimal getStockValue() {
        return stockValue;
    }
}
