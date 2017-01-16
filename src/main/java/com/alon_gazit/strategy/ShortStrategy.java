package com.alon_gazit.strategy;

import com.alon_gazit.model.StockData;
import com.alon_gazit.model.SymbolMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alon.g on 10/9/2016.
 */
public class ShortStrategy extends TrendStrategy {
    protected int numOfShortDays;
    protected int numOfStopDays;

    public int getNumOfShortDays() {return numOfShortDays;}

    public void setNumOfShortDays(int numOfShortDays) {this.numOfShortDays = numOfShortDays;}

    public int getNumOfStopDays() {return numOfStopDays;}

    public void setNumOfStopDays(int numOfStopDays) {this.numOfStopDays = numOfStopDays;}


    public ShortStrategy(int numOfShortDays, int numOfStopDays,int numOfDaysForMovingAverage) {
        this.numOfShortDays = numOfShortDays;
        this.numOfStopDays = numOfStopDays;
        this.numOfDaysForMovingAverage = numOfDaysForMovingAverage;
    }

    @Override
    protected double getStrategyEntryPrice(List<String[]> symbolHistory) {
        return getMinValue(symbolHistory,numOfShortDays);
    }

    @Override
    protected double getStrategyStopLost(List<String[]> symbolHistory) {
        return getMaxValue(symbolHistory,numOfStopDays);
    }

    @Override
    public List<SymbolMessage> sendMessageDueToUpdate(double lastPriceUpdate, StockData stockData) {
        SymbolMessage message = null;
        if (stockData.getLastPrice()>lastPriceUpdate && stockData.getEntryPrice()>lastPriceUpdate){
            message = new SymbolMessage();
            message.setSymbol(stockData.getSymbol().getName());
            message.setText(" Pass "+ numOfShortDays +" days lowest low. Enter Position");
            message.setPrice(lastPriceUpdate);
        } else if (stockData.getLastPrice()<lastPriceUpdate && stockData.getExitPrice()<lastPriceUpdate){
            message = new SymbolMessage();
            message.setSymbol(stockData.getSymbol().getName());
            message.setText(" Pass "+ numOfStopDays +" days highest high. Exit Position");
            message.setPrice(lastPriceUpdate);
        }
        List<SymbolMessage> ansewr = new ArrayList<>();
        ansewr.add(message);
        return ansewr;
    }

}
