package com.alon_gazit.risk;

import com.alon_gazit.model.ExposureValues;
import com.alon_gazit.model.Symbol;

import java.util.List;

/**
 * Created by alon.g on 10/10/2016.
 */
public class BasicRiskManagement implements RiskManagement {
    private static final long accountSize = 230000;
    private static final double riskPercentage = 0.005;
    @Override
    public ExposureValues getExposureDetails(Symbol symbol, List<String[]> symbolHistory) {
        ExposureValues answer = new ExposureValues();
        answer.setDailyRange(getTrueRange(symbolHistory));
        answer.setPositionSize(getPositionSize(answer.getDailyRange(),Double.parseDouble(symbolHistory.get(1)[4])));
        return answer;
    }

    private long getPositionSize(double range,double lastPrice){
        return Math.round((accountSize)*(riskPercentage/range));
    }

    private double getTrueRange(List<String[]> symbolHistory){
        double sum = 0;
        for (int i=1 ; i<=50 ; i++){
            double high = Double.parseDouble(symbolHistory.get(i)[2]);
            double low = Double.parseDouble(symbolHistory.get(i)[3]);
            double privClose = Double.parseDouble(symbolHistory.get(i+1)[4]);
            double maxVal = Math.max(
                    Math.max(high-low,Math.abs(high-privClose)),
                    Math.abs(low-privClose));
            sum += maxVal;
        }
        return sum/50;

    }

}
