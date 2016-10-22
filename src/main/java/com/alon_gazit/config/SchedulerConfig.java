package com.alon_gazit.config;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.spi.JobFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by alon.g on 10/12/2016.
 */
//@Configuration
public class SchedulerConfig {

    @Bean
    public Scheduler scheduler(){
        Scheduler answer= null;
        try {
            SchedulerFactory sf = new StdSchedulerFactory();
            answer = sf.getScheduler();
        }catch (SchedulerException e){
            e.printStackTrace();
        }
        return answer;
    }



}
