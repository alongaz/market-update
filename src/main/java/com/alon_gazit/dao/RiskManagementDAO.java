package com.alon_gazit.dao;

import com.alon_gazit.model.Symbol;
import com.alon_gazit.risk.BasicRiskManagement;
import com.alon_gazit.risk.RiskManagement;
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
public class RiskManagementDAO {
    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String SQL_SELECT_QUERY = "select * from RISK_MANAGEMENT where STOCK_ID=?";

    public RiskManagement getRiskManagement(Symbol symbol){
        Map<String, Object> row = jdbcTemplate.queryForMap(SQL_SELECT_QUERY,symbol.getId());
        RiskManagement answer = null;
        try {
            Class answerClass = Class.forName((String)row.get("RISK_MANAGEMENT_CLASS"));
            try {
                answer = (RiskManagement)answerClass.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return answer;
    }
}
