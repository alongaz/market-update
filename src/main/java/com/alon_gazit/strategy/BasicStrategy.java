package com.alon_gazit.strategy;

import com.alon_gazit.model.StrategyValues;
import com.alon_gazit.model.Symbol;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by alon.g on 10/9/2016.
 */
public abstract class BasicStrategy implements Strategy {
    protected abstract double getStrategyEntryPrice(List<String[]> symbolHistory);

    protected abstract double getStrategyStopLost(List<String[]> symbolHistory);

    @Override
    public StrategyValues getStrategyValues(Symbol symbol, List<String[]> symbolHistory) {
        StrategyValues answer = new StrategyValues();
        answer.setName(symbol);
        answer.setEntryPrice(getStrategyEntryPrice(symbolHistory));
        answer.setStopLost(getStrategyStopLost(symbolHistory));
        return answer;
    }

    protected double getMaxValue(final List<String[]> symbolHistory, int numOfDays){
        List<Double> array = new ArrayList<Double>();
        for (int i = 1; i <= numOfDays ; i++ )
            array.add(Double.parseDouble(symbolHistory.get(i)[2]));
        return Collections.max(array);
    }

    protected double getMinValue(final List<String[]> symbolHistory, int numOfDays){
        List<Double> array = new ArrayList<Double>();
        for (int i = 1; i <= numOfDays ; i++ )
            array.add(Double.parseDouble(symbolHistory.get(i)[3]));
        return Collections.min(array);
    }
}
