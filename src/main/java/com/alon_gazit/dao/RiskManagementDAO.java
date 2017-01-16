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

    private static final String SQL_SELECT_QUERY = "select * from RISK_MANAGEMENT_MAPPING where ID=?";
    private static final String SQL_SELECT_QUERY_2 = "select * from RISK_MANAGEMENT,RISK_MANAGEMENT_MAPPING where " +
            "RISK_MANAGEMENT.ID= RISK_MANAGEMENT_MAPPING.RISK_MANAGEMENT_ID and MAPPED_ID=?";

    public RiskManagement getRiskManagement(Symbol symbol){
        Map<String, Object> riskManagementMappingRow = jdbcTemplate.queryForMap(SQL_SELECT_QUERY,symbol.getId());

        RiskManagement answer = null;
        try {
            Integer riskManagementID = (Integer)riskManagementMappingRow.get("MAPPED_ID");
            Map<String, Object> riskManagementRow = jdbcTemplate.queryForMap(SQL_SELECT_QUERY_2,riskManagementID);
            Class answerClass = Class.forName((String)riskManagementRow.get("RISK_MANAGEMENT_CLASS"));
            answer = (RiskManagement)answerClass.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return answer;
    }
}
