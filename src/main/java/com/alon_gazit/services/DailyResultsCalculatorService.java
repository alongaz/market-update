package com.alon_gazit.services;

import com.alon_gazit.crawler.YahooHistoryCrawler;
import com.alon_gazit.dao.RiskManagementDAO;
import com.alon_gazit.dao.StockDataDAO;
import com.alon_gazit.dao.StrategyDAO;
import com.alon_gazit.dao.SymbolsDAO;
import com.alon_gazit.model.*;
import com.alon_gazit.risk.RiskManagement;
import com.alon_gazit.strategy.CombinedStrategy;
import com.alon_gazit.strategy.Strategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

    public List<StockData> calcDailyResults(String strategy){
        stockDataDAO.clearTable();
        List<StockData> result = new ArrayList<StockData>();
        CombinedStrategy combinedStrategy = (CombinedStrategy)strategyDAO.getCombinedStrategy(strategy);
        Set<Symbol> symbols = combinedStrategy.getSymbols();
        for (Symbol symbol: symbols) {
            List<StockData> stockDataList = calcDailyResultsForSymbol(symbol,combinedStrategy);
            result.addAll(stockDataList);
        }
        stockDataDAO.insertStockDatas(result);
        return result;
    }

    private List<StockData> calcDailyResultsForSymbol(Symbol symbol,CombinedStrategy combinedStrategy){
        List<StockData> answer = new ArrayList<>();
        List<String[]> symbolHistory = historyCrawler.getHistory(symbol);
       // List<Strategy> strategies = strategyDAO.getStrategy(symbol);
        List<StrategyValues> strategyValues = combinedStrategy.getStrategyValues(symbol,symbolHistory);
       // strategies.forEach(strategy -> strategyValues.addAll(strategy.getStrategyValues(symbol,symbolHistory)));
        RiskManagement riskManagement = riskManagementDAO.getRiskManagement(symbol);
        ExposureValues exposureValues = riskManagement.getExposureDetails(symbol,symbolHistory);
        for (StrategyValues value: strategyValues) {
            StockData result = new StockData();
            result.setSymbol(symbol);
            result.setStrategy(value.getStrategy());
            result.setLastPrice(Double.parseDouble(symbolHistory.get(1)[4]));
            result.setEntryPrice(value.getEntryPrice());
            result.setExitPrice(value.getStopLost());
            result.setPreviousEntryPrice(value.getPreviousEntryPrice());
            result.setPreviousExitPrice(value.getPreviousStopLost());
            result.setRange(exposureValues.getDailyRange());
            result.setPositionSize(exposureValues.getPositionSize());
            result.setMovingAverage(((TrendStrategyValues)value).getMovingAverage());
            answer.add(result);
        }
        return answer;
    }
}
