package com.example.streamer.model;

import java.math.BigDecimal;

public class CombinedStock {

    private BigDecimal currentValue;
    private BigDecimal differenceValue;
    private BigDecimal percentage;
    private String symbol;

    public CombinedStock(BigDecimal currentValue, BigDecimal differenceValue, String symbol) {
        this.currentValue = currentValue;
        this.differenceValue = differenceValue;
        this.symbol = symbol;
    }

    public BigDecimal getCurrentValue() {
        return currentValue;
    }

    public BigDecimal getDifferenceValue() {
        return differenceValue;
    }

    public String getSymbol() {
        return symbol;
    }
}
