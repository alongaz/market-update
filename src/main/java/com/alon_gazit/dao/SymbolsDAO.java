package com.alon_gazit.dao;

import com.alon_gazit.model.Symbol;
import com.alon_gazit.strategy.LongStrategy;
import com.alon_gazit.strategy.ShortStrategy;
import com.alon_gazit.strategy.Strategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by alon.g on 10/22/2016.
 */
@Component
public class SymbolsDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String SQL_SELECT_QUERY = "select * from SYMBOLS";

    private static List<String> SYMBOLS = new ArrayList<String>();

    public List<Symbol> getSymbols(){
        List<Symbol> answer = new ArrayList<Symbol>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(SQL_SELECT_QUERY);
        rows.forEach(row-> answer.add(new Symbol((Integer)row.get("ID"),(String)row.get("NAME"))));
        return answer;
    }
}
