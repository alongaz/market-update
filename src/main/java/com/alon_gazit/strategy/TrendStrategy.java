package com.alon_gazit.strategy;

import com.alon_gazit.model.StrategyValues;
import com.alon_gazit.model.Symbol;
import com.alon_gazit.model.TrendStrategyValues;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by alon.g on 12/19/16.
 */
public abstract class TrendStrategy extends BasicStrategy{

    protected int numOfDaysForMovingAverage;


    @Override
    public List<StrategyValues> getStrategyValues(Symbol symbol, List<String[]> symbolHistory) {
        List<StrategyValues> list = super.getStrategyValues(symbol, symbolHistory);
        for (int i = 0; i < list.size(); i++) {
            TrendStrategyValues values = new TrendStrategyValues(list.get(i));
            values.setMovingAverage(getAverageValue(symbolHistory));
            list.set(i,values);
        }
        return list;
    }

    protected double getAverageValue(final List<String[]> symbolHistory){
        Double sum = 0.0;

        for (int i = 1; i <= numOfDaysForMovingAverage ; i++ )
            sum += Double.parseDouble(symbolHistory.get(i)[6]);
        return sum/numOfDaysForMovingAverage;
    }

    public int getNumOfDaysForMovingAverage() {return numOfDaysForMovingAverage;}

    public void setNumOfDaysForMovingAverage(int numOfDaysForMovingAverage) {this.numOfDaysForMovingAverage = numOfDaysForMovingAverage;}
}