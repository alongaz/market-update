package com.alon_gazit.strategy;

import java.util.List;

/**
 * Created by alon.g on 10/9/2016.
 */
public class LongStrategy extends BasicStrategy{
    private int numOfLongDays;
    private int numOfStopDays;

    public LongStrategy(int numOfLongDays, int numOfStopDays) {
        this.numOfLongDays = numOfLongDays;
        this.numOfStopDays = numOfStopDays;
    }

    @Override
    protected double getStrategyEntryPrice(List<String[]> symbolHistory) {
        return getMaxValue(symbolHistory,numOfLongDays);
    }

    @Override
    protected double getStrategyStopLost(List<String[]> symbolHistory) {
        return getMinValue(symbolHistory, numOfStopDays);
    }
}
