package com.alon_gazit.scheduling;

import com.alon_gazit.services.DailyResultsCalculatorService;
import com.alon_gazit.services.RealtimeResultsCalculatorService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

/**
 * Created by alon.g on 10/23/2016.
 */
@Component
public class RealtimeUpdateJob implements Job {

    @Autowired
    RealtimeResultsCalculatorService realtimeResultsCalculatorService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        realtimeResultsCalculatorService.calcRealtimeResults();
    }
}

