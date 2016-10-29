package com.alon_gazit.services;

import com.alon_gazit.crawler.YahooRealtimeCrawler;
import com.alon_gazit.dao.RiskManagementDAO;
import com.alon_gazit.dao.StockDataDAO;
import com.alon_gazit.dao.StrategyDAO;
import com.alon_gazit.dao.SymbolsDAO;
import com.alon_gazit.messages.WhatAppMessageSender;
import com.alon_gazit.model.StockData;
import com.alon_gazit.model.Symbol;
import com.alon_gazit.model.SymbolMessage;
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
    @Autowired
    StockDataDAO stockDataDAO;
    @Autowired
    WhatAppMessageSender whatAppMessageSender;

    public List<Double> calcRealtimeResults(){
        List<Double> result = new ArrayList<Double>();
        List<Symbol> symbols = symbolsDAO.getSymbols();
        for (Symbol symbol : symbols) {
            StockData stockData = stockDataDAO.getStockData(symbol);
            double lastPrice = calcRealtimeResultsForSymbol(symbol);
            Strategy strategy = strategyDAO.getStrategy(symbol);
            SymbolMessage message = strategy.sendMessageDueToUpdate(lastPrice,stockData);
            if (message != null){
                whatAppMessageSender.sendMessage(message);
            }
            stockDataDAO.updateLastPrice(symbol, lastPrice );
        }
        return result;
    }

    private double calcRealtimeResultsForSymbol(Symbol symbol){
        return historyCrawler.getQuote(symbol);
    }
}
