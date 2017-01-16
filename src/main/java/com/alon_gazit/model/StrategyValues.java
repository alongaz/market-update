package com.alon_gazit.model;

import com.alon_gazit.strategy.Strategy;

/**
 * Created by alon.g on 10/9/2016.
 */
public class StrategyValues {
    private Symbol name;
    private Strategy strategy;
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

    public Strategy getStrategy() {return strategy;}

    public void setStrategy(Strategy strategy) {this.strategy = strategy;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StrategyValues values = (StrategyValues) o;

        if (Double.compare(values.entryPrice, entryPrice) != 0) return false;
        if (Double.compare(values.stopLost, stopLost) != 0) return false;
        if (name != null ? !name.equals(values.name) : values.name != null) return false;
        return strategy != null ? strategy.equals(values.strategy) : values.strategy == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name != null ? name.hashCode() : 0;
        result = 31 * result + (strategy != null ? strategy.hashCode() : 0);
        temp = Double.doubleToLongBits(entryPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(stopLost);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
