package com.alon_gazit.strategy;

import com.alon_gazit.model.StockData;
import com.alon_gazit.model.StrategyValues;
import com.alon_gazit.model.Symbol;
import com.alon_gazit.model.SymbolMessage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by alon.g on 10/9/2016.
 */
public abstract class BasicStrategy implements Strategy {
    protected abstract double getStrategyEntryPrice(List<String[]> symbolHistory);

    protected abstract double getStrategyStopLost(List<String[]> symbolHistory);

    public abstract List<SymbolMessage> sendMessageDueToUpdate(double lastPriceUpdate , StockData stockData);

    @Override
    public List<StrategyValues> getStrategyValues(Symbol symbol, List<String[]> symbolHistory) {
        StrategyValues values = new StrategyValues();
        values.setName(symbol);
        values.setStrategy(this);
        values.setEntryPrice(getStrategyEntryPrice(symbolHistory));
        values.setStopLost(getStrategyStopLost(symbolHistory));
        List<StrategyValues> answer = new ArrayList<>();
        answer.add(values);
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
