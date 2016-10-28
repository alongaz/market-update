package com.alon_gazit.risk;

import com.alon_gazit.model.ExposureValues;
import com.alon_gazit.model.Symbol;

import java.util.List;

/**
 * Created by alon.g on 10/10/2016.
 */
public interface RiskManagement {
    public  ExposureValues getExposureDetails(Symbol symbol, List<String[]> symbolHistory);

}
