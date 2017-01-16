package com.alon_gazit.model;

/**
 * Created by alon.g on 12/19/16.
 */
public class TrendStrategyValues extends StrategyValues {

    private double movingAverage;

    public TrendStrategyValues(StrategyValues copy){
        this.setName(copy.getName());
        this.setEntryPrice(copy.getEntryPrice());
        this.setStopLost(copy.getStopLost());
        this.setStrategy(copy.getStrategy());
    }

    public double getMovingAverage() {return movingAverage;}

    public void setMovingAverage(double movingAverage) {this.movingAverage = movingAverage;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TrendStrategyValues that = (TrendStrategyValues) o;

        return Double.compare(that.movingAverage, movingAverage) == 0;

    }

    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(movingAverage);
        return (int) (temp ^ (temp >>> 32));
    }
}
