package com.alon_gazit.controllers;

import com.google.common.collect.Collections2;
import com.opencsv.CSVReader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/api")
public class TestController {
  private static final String YAHOO_URL_PREFIX= "http://chart.finance.yahoo.com/table.csv?s=";
  private static final String YAHOO_URL_SUFIX= "&a=9&b=6&c=2015&d=5&e=14&f=2048&g=d&ignore=.csv";

  public class TestObject {
    private String s;

    public String getS() {
      return s;
    }

    public void setS(String s) {
      this.s = s;
    }
  }
  @RequestMapping(value = "/test", method = RequestMethod.GET)
  @ResponseBody
  public TestObject test() {
    TestObject ret = new TestObject();
    ret.s="Hello works";
    return ret;
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
    List<String[]> symbolHistory = getHistory(symbol);

    result.setSymbol(symbol);
    result.set_10DaysHigh(getMaxValue(symbolHistory,10));
    result.set_10DaysLow(getMinValue(symbolHistory,10));
    result.set_20DaysHigh(getMaxValue(symbolHistory,20));
    result.set_20DaysLow(getMinValue(symbolHistory,20));
    result.set_40DaysHigh(getMaxValue(symbolHistory,40));
    result.set_40DaysLow(getMinValue(symbolHistory,40));
    result.setLastPrice(Double.parseDouble(symbolHistory.get(1)[4]));
    result.setRange(getTrueRange(symbolHistory));
    result.setPositionSize(getPositionSize(result.getRange()));
    return result;
  }

  private long getPositionSize(double range){
    return Math.round(2700/range);
  }

  private double getTrueRange(List<String[]> symbolHistory){
    double sum = 0;
    for (int i=1 ; i<=20 ; i++){
      double high = Double.parseDouble(symbolHistory.get(i)[2]);
      double low = Double.parseDouble(symbolHistory.get(i)[3]);
      double privClose = Double.parseDouble(symbolHistory.get(i+1)[4]);
      double maxVal = Math.max(
                            Math.max(high-low,Math.abs(high-privClose)),
                            Math.abs(low-privClose));
      sum += maxVal;
    }
    return sum/20;

  }



  private double getMaxValue(final List<String[]> symbolHistory, int numOfDays){
    List<Double> array = new ArrayList<Double>();
    for (int i = 1; i <= numOfDays ; i++ )
      array.add(Double.parseDouble(symbolHistory.get(i)[2]));
    return Collections.max(array);
  }

  private double getMinValue(final List<String[]> symbolHistory, int numOfDays){
    List<Double> array = new ArrayList<Double>();
    for (int i = 1; i <= numOfDays ; i++ )
      array.add(Double.parseDouble(symbolHistory.get(i)[3]));
    return Collections.min(array);
  }

  private List<String[]> getHistory(String symbol){
    String yahooURL = YAHOO_URL_PREFIX+symbol+YAHOO_URL_SUFIX;
    CSVReader reader = null;
    List<String[]> result = null;
    try {
     // String file = new URL(yahooUR;
      URL yahoo = new URL(yahooURL);
      BufferedReader in = new BufferedReader(
              new InputStreamReader(yahoo.openStream()));
      reader = new CSVReader(in);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    try {
      result = reader.readAll();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return result;
  }

  @RequestMapping(value = "/symbols", method = RequestMethod.GET)
  @ResponseBody
  public List<String> getSymbols(){
    return Arrays.asList(
            "SPY",
            "EEM",
            "DXJ",
            "GDXJ",
            "USO",
            "SVXY",
            "TLT",
            "YCS",
            "EUO"
    );
  }

}
