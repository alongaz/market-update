package com.alon_gazit.model;

/**
 * Created by alon.g on 10/10/2016.
 */
public class ExposureValues {
    private double dailyRange;
    private long positionSize;

    public double getDailyRange() {
        return dailyRange;
    }

    public void setDailyRange(double dailyRange) {
        this.dailyRange = dailyRange;
    }

    public long getPositionSize() {
        return positionSize;
    }

    public void setPositionSize(long positionSize) {
        this.positionSize = positionSize;
    }
}
