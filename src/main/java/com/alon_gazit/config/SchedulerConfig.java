package com.alon_gazit.config;

import com.alon_gazit.scheduling.AutowiringSpringBeanJobFactory;
import com.alon_gazit.scheduling.NightlyJob;
import com.alon_gazit.scheduling.RealtimeUpdateJob;
import org.quartz.*;
import org.quartz.CronTrigger;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.CronTriggerImpl;
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
import org.springframework.scheduling.support.*;

import javax.sql.DataSource;
import java.io.IOException;
import java.text.ParseException;
import java.util.Properties;

/**
 * Created by alon.g on 10/12/2016.
 */
@Configuration
public class SchedulerConfig {

    @Bean
    public Scheduler scheduler(ApplicationContext applicationContext){
        Scheduler answer= null;
        try {
            SchedulerFactory sf = new StdSchedulerFactory();
            answer = sf.getScheduler();
            AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
            jobFactory.setApplicationContext(applicationContext);
            answer.setJobFactory(jobFactory);
            answer.start();
            answer.scheduleJob(nigthlyJob(),nigthlyTrigger());
            answer.triggerJob(new JobKey("NightlyJob"));
            answer.scheduleJob(realtimeJob(),realtimeTrigger());
        }catch (SchedulerException e){
            e.printStackTrace();
        }

        return answer;
    }

    @Bean
    public JobDetail nigthlyJob(){
        JobDetailImpl answer = new JobDetailImpl();
        answer.setJobClass(NightlyJob.class);
        answer.setKey(new JobKey("NightlyJob"));
        return answer;
    }

    @Bean
    public CronTrigger  nigthlyTrigger(){
        CronTriggerImpl trigger = new CronTriggerImpl();
        try {
            trigger.setKey(new TriggerKey("NightlyTrigger"));
            trigger.setCronExpression("0 0 3am * * ?");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return trigger;

    }

    @Bean
    public JobDetail realtimeJob(){
        JobDetailImpl answer = new JobDetailImpl();
        answer.setJobClass(RealtimeUpdateJob.class);
        answer.setKey(new JobKey("RealtimeJob"));
        return answer;
    }

    @Bean
    public CronTrigger  realtimeTrigger(){
        CronTriggerImpl trigger = new CronTriggerImpl();
        try {
            trigger.setKey(new TriggerKey("RealtimeTrigger"));
            trigger.setCronExpression("0 0/20 6-15 * * ?");
         //   trigger.setCronExpression("0/20 * * * * ?");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return trigger;

    }





}
