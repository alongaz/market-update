package com.alon_gazit.controllers;

import com.alon_gazit.dao.SymbolsDAO;
import com.alon_gazit.model.StockData;
import com.alon_gazit.model.Symbol;
import com.alon_gazit.services.DailyResultsCalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
@RequestMapping("/api")
public class TestController {


    @Autowired
    private SymbolsDAO symbolsDAO;
    @Autowired
    DailyResultsCalculatorService dailyResultsCalculatorService;

    @RequestMapping(value = "/dailyResults", method = RequestMethod.GET)
  @ResponseBody
  public List<StockData> calcDailyResults(){
        return dailyResultsCalculatorService.calcDailyResults();
  }


  @RequestMapping(value = "/symbols", method = RequestMethod.GET)
  @ResponseBody
  public List<Symbol> getSymbols(){
    //list of symbols
    return symbolsDAO.getSymbols();
  }

}
