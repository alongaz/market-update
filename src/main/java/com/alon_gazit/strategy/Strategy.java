package com.alon_gazit.strategy;

import com.alon_gazit.model.StrategyValues;
import com.alon_gazit.model.Symbol;

import java.util.List;

/**
 * Created by alon.g on 10/9/2016.
 */
public interface Strategy {
    public StrategyValues getStrategyValues(Symbol symbol, List<String[]> symbolHistory);
}
