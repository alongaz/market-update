package com.alon_gazit.risk;

import com.alon_gazit.model.ExposureValues;

import java.util.List;

/**
 * Created by alon.g on 10/10/2016.
 */
public interface RiskManagement {
    public  ExposureValues getExposureDetails(String symbol, List<String[]> symbolHistory);

}
