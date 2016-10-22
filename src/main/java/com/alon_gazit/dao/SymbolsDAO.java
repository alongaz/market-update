package com.alon_gazit.dao;

import com.alon_gazit.strategy.LongStrategy;
import com.alon_gazit.strategy.ShortStrategy;
import com.alon_gazit.strategy.Strategy;
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

    private static List<String> SYMBOLS = new ArrayList<String>();

   static {
        SYMBOLS.add("SPY");
        SYMBOLS.add("EEM");
        SYMBOLS.add("DXJ");
        SYMBOLS.add("GDXJ");
        SYMBOLS.add("JNUG");
        SYMBOLS.add("USO");
        SYMBOLS.add("VXX");
        SYMBOLS.add("UVXY");
        SYMBOLS.add("TLT");
        SYMBOLS.add("YCS");
        SYMBOLS.add("EUO");
    }

    public List<String> getSymbols(){
        return SYMBOLS;
    }
}
