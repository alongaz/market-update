package com.alon_gazit.crawler;

import com.alon_gazit.model.StockDailyInfo;
import com.alon_gazit.model.Symbol;
import com.opencsv.CSVReader;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by alon.g on 10/12/2016.
 */
@Component
public class YahooRealtimeCrawler {

    private static final String YAHOO_URL_PREFIX= "http://download.finance.yahoo.com/d/quotes.csv?s=";
    private static final String YAHOO_URL_SUFIX= "&f=sl1d1t1c1ohgv&e=.csv&columns='symbol,price,date,time,change,col1,high,low,col2";

    public StockDailyInfo getQuote(Symbol symbol){
        String yahooURL = YAHOO_URL_PREFIX+symbol.getName()+YAHOO_URL_SUFIX;

        CSVReader reader = null;
        String[] result = null;
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
            result = reader.readNext();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(result[0]+","+result[1]+","+result[2]+","+result[3]);
        StockDailyInfo answer = new StockDailyInfo();
        answer.setSymbol(symbol);
        answer.setLastPrice(Double.parseDouble(result[1]));
        answer.setDailyHighPrice(Double.parseDouble(result[6]));
        answer.setDailyLowPrice(Double.parseDouble(result[7]));
        return answer;
    }

    public static void main(String[] args){
        YahooRealtimeCrawler realtimeCrawler = new YahooRealtimeCrawler();
        realtimeCrawler.getQuote(new Symbol(1,"SPY"));
    }

}
