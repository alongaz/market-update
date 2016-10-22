package com.alon_gazit.dao;

import com.alon_gazit.risk.BasicRiskManagement;
import com.alon_gazit.risk.RiskManagement;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by alon.g on 10/22/2016.
 */
@Component
public class RiskManagementDAO {
    private static Map<String,RiskManagement> riskManagementMap = new HashMap<String, RiskManagement>();

    static {
        riskManagementMap.put("SPY", new BasicRiskManagement());
        riskManagementMap.put("EEM", new BasicRiskManagement());
        riskManagementMap.put("DXJ", new BasicRiskManagement());
        riskManagementMap.put("GDXJ", new BasicRiskManagement());
        riskManagementMap.put("JNUG", new BasicRiskManagement());
        riskManagementMap.put("USO", new BasicRiskManagement());
        riskManagementMap.put("VXX", new BasicRiskManagement());
        riskManagementMap.put("UVXY", new BasicRiskManagement());
        riskManagementMap.put("TLT", new BasicRiskManagement());
        riskManagementMap.put("YCS", new BasicRiskManagement());
        riskManagementMap.put("EUO" , new BasicRiskManagement());
     }

    public RiskManagement getRiskManagement(String symbol){
        return riskManagementMap.get(symbol);
    }
}
