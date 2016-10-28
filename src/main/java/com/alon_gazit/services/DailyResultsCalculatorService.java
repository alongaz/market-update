package com.alon_gazit.services;

import com.alon_gazit.crawler.YahooHistoryCrawler;
import com.alon_gazit.dao.RiskManagementDAO;
import com.alon_gazit.dao.StrategyDAO;
import com.alon_gazit.dao.SymbolsDAO;
import com.alon_gazit.model.CalculationResult;
import com.alon_gazit.model.ExposureValues;
import com.alon_gazit.model.StrategyValues;
import com.alon_gazit.model.Symbol;
import com.alon_gazit.risk.RiskManagement;
import com.alon_gazit.strategy.Strategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alon.g on 10/22/2016.
 */
@Component
public class DailyResultsCalculatorService {

    @Autowired
    private YahooHistoryCrawler historyCrawler;
    @Autowired
    private RiskManagementDAO riskManagementDAO;
    @Autowired
    private SymbolsDAO symbolsDAO;
    @Autowired
    private  StrategyDAO strategyDAO;

    public List<CalculationResult> calcDailyResults(){
        List<CalculationResult> result = new ArrayList<CalculationResult>();
        List<Symbol> symbols = symbolsDAO.getSymbols();
        symbols.forEach(symbol-> result.add(calcDailyResultsForSymbol(symbol)) );
        return result;
    }

    private CalculationResult calcDailyResultsForSymbol(Symbol symbol){
        CalculationResult result = new CalculationResult();
        List<String[]> symbolHistory = historyCrawler.getHistory(symbol.getName());
        Strategy strategy = strategyDAO.getStrategy(symbol);
        StrategyValues strategyValues =strategy.getStrategyValues(symbol.getName(),symbolHistory);
        RiskManagement riskManagement = riskManagementDAO.getRiskManagement(symbol);
        ExposureValues exposureValues = riskManagement.getExposureDetails(symbol.getName(),symbolHistory);
        result.setSymbol(symbol.getName());
        result.setLastPrice(Double.parseDouble(symbolHistory.get(1)[4]));
        result.setEntryPrice(strategyValues.getEntryPrice());
        result.setExitPrice(strategyValues.getStopLost());
        result.setRange(exposureValues.getDailyRange());
        result.setPositionSize(exposureValues.getPositionSize());
        return result;
    }
}
