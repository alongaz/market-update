package com.alon_gazit.dao;

import com.alon_gazit.model.Symbol;
import com.alon_gazit.strategy.LongStrategy;
import com.alon_gazit.strategy.ShortStrategy;
import com.alon_gazit.strategy.Strategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * Created by alon.g on 10/22/2016.
 */
@Component
public class StrategyDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String SQL_SELECT_QUERY = "select * from STRATEGIES where STOCK_ID=?";

    public Strategy getStrategy(Symbol symbol){
        Map<String, Object> row = jdbcTemplate.queryForMap(SQL_SELECT_QUERY,symbol.getId());
        Strategy answer = null;
        try {
            Class answerClass = Class.forName((String)row.get("STRATEGY_CLASS"));
            String params = (String)row.get("CLASS_PARAMS");
            List paramsValues = new ArrayList<Object>();
            try {
                StringTokenizer tokenizer = new StringTokenizer(params,",");
                while (tokenizer.hasMoreTokens()){
                    String param = tokenizer.nextToken();
                    StringTokenizer paramElements = new StringTokenizer(param,"=");
                    String paramName = paramElements.nextToken();
                    String paramValue = paramElements.nextToken();
                    paramsValues.add(Integer.parseInt(paramValue));

                }
                answer = (Strategy) answerClass.getConstructors()[0].newInstance(paramsValues.toArray());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return answer;
    }

}
