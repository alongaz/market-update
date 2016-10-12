package com.alon_gazit.controllers;

import com.alon_gazit.crawler.YahooHistoryCrawler;
import com.alon_gazit.model.CalculationResult;
import com.alon_gazit.model.ExposureValues;
import com.alon_gazit.model.StrategyValues;
import com.alon_gazit.risk.BasicRiskManagement;
import com.alon_gazit.risk.RiskManagement;
import com.alon_gazit.strategy.LongStrategy;
import com.alon_gazit.strategy.ShortStrategy;
import com.alon_gazit.strategy.Strategy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
@RequestMapping("/api")
public class TestController {

  private static Map<String,Strategy> strategyMap = new HashMap<String, Strategy>();
  private static Map<String,RiskManagement> riskManagementMap = new HashMap<String, RiskManagement>();
  public YahooHistoryCrawler historyCrawler = new YahooHistoryCrawler();

  static {
      strategyMap.put("SPY", new ShortStrategy(40,20));
      strategyMap.put("EEM", new ShortStrategy(40,20));
      strategyMap.put("DXJ", new ShortStrategy(40,20));
      strategyMap.put("GDXJ", new LongStrategy(20,10));
      strategyMap.put("JNUG", new LongStrategy(20,10));
      strategyMap.put("USO", new ShortStrategy(20,10));
      strategyMap.put("VXX", new LongStrategy(20,5));
      strategyMap.put("UVXY", new LongStrategy(20,5));
      strategyMap.put("TLT", new ShortStrategy(40,20));
      strategyMap.put("YCS", new LongStrategy(40,20));
      strategyMap.put("EUO" , new LongStrategy(40,20));


      riskManagementMap.put("SPY", new BasicRiskManagement());
      riskManagementMap.put("EEM", new BasicRiskManagement());
      riskManagementMap.put("DXJ", new BasicRiskManagement());
      riskManagementMap.put("GDXJ", new BasicRiskManagement());
      riskManagementMap.put("JNUG", new BasicRiskManagement());
      riskManagementMap.put("USO", new BasicRiskManagement());
      riskManagementMap.put("VXX", new BasicRiskManagement());
      riskManagementMap.put("UVXY", new BasicRiskManagement());
      riskManagementMap.put("TLT", new BasicRiskManagement());
      riskManagementMap.put("YCS", new BasicRiskManagement());
      riskManagementMap.put("EUO" , new BasicRiskManagement());
  }

    @RequestMapping(value = "/dailyResults", method = RequestMethod.GET)
  @ResponseBody
  public List<CalculationResult> calcDailyResults(){
    List<CalculationResult> result = new ArrayList<CalculationResult>();
    List<String> symbols = getSymbols();
    symbols.forEach(symbol-> result.add(calcDailyResultsForSymbol(symbol)) );
    return result;
  }

  private CalculationResult calcDailyResultsForSymbol(String symbol){
    CalculationResult result = new CalculationResult();
    List<String[]> symbolHistory = historyCrawler.getHistory(symbol);
    Strategy strategy = strategyMap.get(symbol);
      StrategyValues strategyValues =strategy.getStrategyValues(symbol,symbolHistory);
    RiskManagement riskManagement = riskManagementMap.get(symbol);
      ExposureValues exposureValues = riskManagement.getExposureDetails(symbol,symbolHistory);
    result.setSymbol(symbol);
    result.setLastPrice(Double.parseDouble(symbolHistory.get(1)[4]));
    result.setEntryPrice(strategyValues.getEntryPrice());
    result.setExitPrice(strategyValues.getStopLost());
    result.setRange(exposureValues.getDailyRange());
    result.setPositionSize(exposureValues.getPositionSize());
    return result;
  }


  @RequestMapping(value = "/symbols", method = RequestMethod.GET)
  @ResponseBody
  public List<String> getSymbols(){
    //list of symbols
    return Arrays.asList(strategyMap.keySet().toArray(new String[0]));
  }

}
