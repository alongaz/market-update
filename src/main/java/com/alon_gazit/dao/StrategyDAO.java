package com.alon_gazit.dao;

import com.alon_gazit.strategy.LongStrategy;
import com.alon_gazit.strategy.ShortStrategy;
import com.alon_gazit.strategy.Strategy;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by alon.g on 10/22/2016.
 */
@Component
public class StrategyDAO {

    private static Map<String,Strategy> strategyMap = new HashMap<String, Strategy>();

    static {
        strategyMap.put("SPY", new ShortStrategy(40, 20));
        strategyMap.put("EEM", new ShortStrategy(40, 20));
        strategyMap.put("DXJ", new ShortStrategy(40, 20));
        strategyMap.put("GDXJ", new LongStrategy(20, 10));
        strategyMap.put("JNUG", new LongStrategy(20, 10));
        strategyMap.put("USO", new ShortStrategy(20, 10));
        strategyMap.put("VXX", new LongStrategy(20, 5));
        strategyMap.put("UVXY", new LongStrategy(20, 5));
        strategyMap.put("TLT", new ShortStrategy(40, 20));
        strategyMap.put("YCS", new LongStrategy(40, 20));
        strategyMap.put("EUO", new LongStrategy(40, 20));
    }

    public Strategy getStrategy(String symbol){
        return strategyMap.get(symbol);
    }
}
