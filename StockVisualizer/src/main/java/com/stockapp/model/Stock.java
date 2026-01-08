package com.stockapp.model;

public class Stock {
    private String stockName;
    private double stockPrice;
    private int stockQuantity;

    public Stock() {
    }

    public Stock(String stockName, double stockPrice, int stockQuantity) {
        this.stockName = stockName;
        this.stockPrice = stockPrice;
        this.stockQuantity = stockQuantity;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public double getStockPrice() {
        return stockPrice;
    }

    public void setStockPrice(double stockPrice) {
        this.stockPrice = stockPrice;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
}
