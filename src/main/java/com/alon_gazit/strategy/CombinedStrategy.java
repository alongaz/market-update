package com.alon_gazit.strategy;

import com.alon_gazit.model.StockData;
import com.alon_gazit.model.StrategyValues;
import com.alon_gazit.model.Symbol;
import com.alon_gazit.model.SymbolMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by alon.g on 12/19/16.
 */
public class CombinedStrategy implements Strategy {
    Map<Symbol,List<Strategy>> symbolsMap;

    public CombinedStrategy(Map<Symbol,List<Strategy>> symbolsMap){
        this.symbolsMap = symbolsMap;
    }

    @Override
    public List<StrategyValues> getStrategyValues(Symbol symbol, List<String[]> symbolHistory) {
        List<StrategyValues> answer = new ArrayList<>();
        List<Strategy> strategies = symbolsMap.get(symbol);
        for (Strategy strategy: strategies)
            answer.addAll(strategy.getStrategyValues(symbol,symbolHistory));
        return answer;
    }

    @Override
    public List<SymbolMessage> sendMessageDueToUpdate(double lastPriceUpdate, StockData stockData) {
        List<SymbolMessage> answer = new ArrayList<>();
        List<Strategy> strategies = symbolsMap.get(stockData.getSymbol());
        for (Strategy strategy: strategies)
            answer.addAll(strategy.sendMessageDueToUpdate(lastPriceUpdate,stockData));
        return answer;
    }

    public Set<Symbol> getSymbols(){
        return symbolsMap.keySet();
    }
}
