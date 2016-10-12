package com.alon_gazit.risk;

import com.alon_gazit.model.ExposureValues;

import java.util.List;

/**
 * Created by alon.g on 10/10/2016.
 */
public class BasicRiskManagement implements RiskManagement {
    private static final long accountSize = 8100;
    @Override
    public ExposureValues getExposureDetails(String symbol, List<String[]> symbolHistory) {
        ExposureValues answer = new ExposureValues();
        answer.setDailyRange(getTrueRange(symbolHistory));
        answer.setPositionSize(getPositionSize(answer.getDailyRange()));
        return answer;
    }

    private long getPositionSize(double range){
        return Math.round(accountSize/range);
    }

    private double getTrueRange(List<String[]> symbolHistory){
        double sum = 0;
        for (int i=1 ; i<=20 ; i++){
            double high = Double.parseDouble(symbolHistory.get(i)[2]);
            double low = Double.parseDouble(symbolHistory.get(i)[3]);
            double privClose = Double.parseDouble(symbolHistory.get(i+1)[4]);
            double maxVal = Math.max(
                    Math.max(high-low,Math.abs(high-privClose)),
                    Math.abs(low-privClose));
            sum += maxVal;
        }
        return sum/20;

    }

}
