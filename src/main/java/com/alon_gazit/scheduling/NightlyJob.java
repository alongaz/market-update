package com.alon_gazit.scheduling;

import com.alon_gazit.controllers.TestController;
import com.alon_gazit.crawler.YahooHistoryCrawler;
import com.alon_gazit.model.StockData;
import com.alon_gazit.services.DailyResultsCalculatorService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by alon.g on 10/19/2016.
 */
public class NightlyJob implements Job {

    @Autowired
    DailyResultsCalculatorService dailyResultsCalculatorService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        List<StockData> shortTermData = dailyResultsCalculatorService.calcDailyResults("Short Term Turtle");
        List<StockData> longTermData =dailyResultsCalculatorService.calcDailyResults("Long Term Turtle");
    }
}
