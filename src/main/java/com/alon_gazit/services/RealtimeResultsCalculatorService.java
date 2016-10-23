package com.alon_gazit.services;

import com.alon_gazit.crawler.YahooHistoryCrawler;
import com.alon_gazit.crawler.YahooRealtimeCrawler;
import com.alon_gazit.dao.RiskManagementDAO;
import com.alon_gazit.dao.StrategyDAO;
import com.alon_gazit.dao.SymbolsDAO;
import com.alon_gazit.model.CalculationResult;
import com.alon_gazit.model.ExposureValues;
import com.alon_gazit.model.StrategyValues;
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
public class RealtimeResultsCalculatorService {

    @Autowired
    private YahooRealtimeCrawler historyCrawler;
    @Autowired
    private RiskManagementDAO riskManagementDAO;
    @Autowired
    private SymbolsDAO symbolsDAO;
    @Autowired
    StrategyDAO strategyDAO;

    public List<Double> calcRealtimeResults(){
        List<Double> result = new ArrayList<Double>();
        List<String> symbols = symbolsDAO.getSymbols();
        symbols.forEach(symbol-> result.add(calcRealtimeResultsForSymbol(symbol)) );
        return result;
    }

    private double calcRealtimeResultsForSymbol(String symbol){
        return historyCrawler.getQuote(symbol);
    }
}
