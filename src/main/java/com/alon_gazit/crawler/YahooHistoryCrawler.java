package com.alon_gazit.crawler;

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
public class YahooHistoryCrawler {
    private static final String YAHOO_URL_PREFIX= "http://chart.finance.yahoo.com/table.csv?s=";
    private static final String YAHOO_URL_SUFIX= "&a=9&b=6&c=2015&d=5&e=14&f=2048&g=d&ignore=.csv";

    public List<String[]> getHistory(String symbol){
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

}
