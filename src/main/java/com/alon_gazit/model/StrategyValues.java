package com.alon_gazit.model;

/**
 * Created by alon.g on 10/9/2016.
 */
public class StrategyValues {
    private Symbol name;
    private double entryPrice;
    private double stopLost;

    public Symbol getName() {
        return name;
    }

    public void setName(Symbol name) {
        this.name = name;
    }

    public double getEntryPrice() {
        return entryPrice;
    }

    public void setEntryPrice(double entryPrice) {
        this.entryPrice = entryPrice;
    }

    public double getStopLost() {
        return stopLost;
    }

    public void setStopLost(double stopLost) {
        this.stopLost = stopLost;
    }
}
