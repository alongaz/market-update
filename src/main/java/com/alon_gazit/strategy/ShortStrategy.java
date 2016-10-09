package com.alon_gazit.strategy;

import java.util.List;

/**
 * Created by alon.g on 10/9/2016.
 */
public class ShortStrategy extends BasicStrategy {
    private int numOfShortDays;
    private int numOfStopDays;

    public ShortStrategy(int numOfShortDays, int numOfStopDays) {
        this.numOfShortDays = numOfShortDays;
        this.numOfStopDays = numOfStopDays;
    }

    @Override
    protected double getStrategyEntryPrice(List<String[]> symbolHistory) {
        return getMinValue(symbolHistory,numOfShortDays);
    }

    @Override
    protected double getStrategyStopLost(List<String[]> symbolHistory) {
        return getMaxValue(symbolHistory,numOfStopDays);
    }
}
