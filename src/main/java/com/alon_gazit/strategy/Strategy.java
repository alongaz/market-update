package com.alon_gazit.strategy;

import com.alon_gazit.model.StrategyValues;

import java.util.List;

/**
 * Created by alon.g on 10/9/2016.
 */
public interface Strategy {
    public StrategyValues getStrategyValues(String symbol, List<String[]> symbolHistory);
}
