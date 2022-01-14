package com.example.streamer.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class UserInterest {

    private int id;
    private int userId;
    private LocalDateTime createdDate;
    private String symbol;
    private String stockChangeDirection;
    private BigDecimal stockChangePercentage;

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getStockChangeDirection() {
        return stockChangeDirection;
    }

    public BigDecimal getStockChangePercentage() {
        return stockChangePercentage;
    }
}
