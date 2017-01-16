package com.alon_gazit.dao;

import com.alon_gazit.model.Symbol;
import com.alon_gazit.strategy.CombinedStrategy;
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

    @Autowired
    SymbolsDAO symbolsDAO;

    private static final String SQL_SELECT_QUERY = "select * from STRATEGIES,STRATEGIES_MAPPING where " +
            "STRATEGIES.ID=STRATEGIES_MAPPING.STRATEGY_ID and MAPPING_TYPE='INVESTMENT_TYPE' and MAPPED_ID=?";
    private static final String GET_COMBINED_STRATEGY_QUERY = "select * from STRATEGIES where STRATEGY_NAME=?";
    private static final String GET_COMBINED_STRATEGY_QUERY2 = "select * from STRATEGIES where ID=?";
    private static final String GET_STRATEGY_MAPPING_QUERY = "select * from STRATEGIES_MAPPING where " +
            "MAPPING_TYPE='COMBINED_TYPE' and STRATEGY_ID=?";
    private static final String GET_STRATEGY_MAPPING_QUERY2 = "select * from STRATEGIES_MAPPING where " +
            "MAPPING_TYPE='INVESTMENT_TYPE' and ID=?";


    public List<Strategy> getStrategy(Symbol symbol){
        List<Strategy> answer = new ArrayList<>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(SQL_SELECT_QUERY,symbol.getId());
        rows.forEach(row-> answer.add(getStrategyInstance(row)));
        return answer;
    }

    public Strategy getCombinedStrategy(String stategyName){
        Map<String, Object> row = jdbcTemplate.queryForMap(GET_COMBINED_STRATEGY_QUERY,stategyName);
        Integer strategyID = (Integer)row.get("ID");
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(GET_STRATEGY_MAPPING_QUERY,strategyID);
        List<Map<String,Object>> strategyRows = new ArrayList<>();
        rows.forEach(mapedRow-> strategyRows.add(jdbcTemplate.queryForMap(GET_STRATEGY_MAPPING_QUERY2,mapedRow.get("MAPPED_ID"))) );
        Map<Symbol,List<Strategy>> strategies = new HashMap<>();
        for (Map<String,Object> startegyMappingRow :strategyRows){
            Map<String,Object> strategyRow = jdbcTemplate.queryForMap(GET_COMBINED_STRATEGY_QUERY2,startegyMappingRow.get("STRATEGY_ID"));
            Strategy strategy = getStrategyInstance(strategyRow);
            Symbol symbol = symbolsDAO.getSymbols((Integer)startegyMappingRow.get("MAPPED_ID"));
            List<Strategy> strategyList = strategies.get(symbol);
            if (strategyList == null)
                    strategyList = new ArrayList<>();
            strategyList.add(strategy);
            strategies.put(symbol,strategyList);
        }
        CombinedStrategy answer = new CombinedStrategy(strategies);
        return answer;
    }

    private Strategy getStrategyInstance (Map<String, Object> row){
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
