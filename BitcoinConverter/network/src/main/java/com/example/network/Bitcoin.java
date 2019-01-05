package com.example.network;

import java.math.BigDecimal;

public class Bitcoin {
    private BigDecimal price;

    public Bitcoin() {
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
