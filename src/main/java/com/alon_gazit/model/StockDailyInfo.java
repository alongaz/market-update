package com.alon_gazit.model;

/**
 * Created by alon.g on 10/29/2016.
 */
public class StockDailyInfo {
    private Symbol symbol;
    private double lastPrice;
    private double dailyHighPrice;
    private double dailyLowPrice;

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public double getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(double lastPrice) {
        this.lastPrice = lastPrice;
    }

    public double getDailyHighPrice() {
        return dailyHighPrice;
    }

    public void setDailyHighPrice(double dailyHighPrice) {
        this.dailyHighPrice = dailyHighPrice;
    }

    public double getDailyLowPrice() {
        return dailyLowPrice;
    }

    public void setDailyLowPrice(double dailyLowPrice) {
        this.dailyLowPrice = dailyLowPrice;
    }
}
