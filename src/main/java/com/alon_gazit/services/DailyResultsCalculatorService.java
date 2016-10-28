package com.alon_gazit.services;

import com.alon_gazit.crawler.YahooHistoryCrawler;
import com.alon_gazit.dao.RiskManagementDAO;
import com.alon_gazit.dao.StockDataDAO;
import com.alon_gazit.dao.StrategyDAO;
import com.alon_gazit.dao.SymbolsDAO;
import com.alon_gazit.model.StockData;
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
    @Autowired
    private StockDataDAO stockDataDAO;

    public List<StockData> calcDailyResults(){
        stockDataDAO.clearTable();
        List<StockData> result = new ArrayList<StockData>();
        List<Symbol> symbols = symbolsDAO.getSymbols();
        for (Symbol symbol: symbols) {
            StockData stockData = calcDailyResultsForSymbol(symbol);
            result.add(stockData);
        }
        stockDataDAO.insertStockDatas(result);
        return result;
    }

    private StockData calcDailyResultsForSymbol(Symbol symbol){
        StockData result = new StockData();
        List<String[]> symbolHistory = historyCrawler.getHistory(symbol);
        Strategy strategy = strategyDAO.getStrategy(symbol);
        StrategyValues strategyValues =strategy.getStrategyValues(symbol,symbolHistory);
        RiskManagement riskManagement = riskManagementDAO.getRiskManagement(symbol);
        ExposureValues exposureValues = riskManagement.getExposureDetails(symbol,symbolHistory);
        result.setSymbol(symbol);
        result.setLastPrice(Double.parseDouble(symbolHistory.get(1)[4]));
        result.setEntryPrice(strategyValues.getEntryPrice());
        result.setExitPrice(strategyValues.getStopLost());
        result.setRange(exposureValues.getDailyRange());
        result.setPositionSize(exposureValues.getPositionSize());
        return result;
    }
}
