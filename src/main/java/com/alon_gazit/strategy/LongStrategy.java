package com.alon_gazit.strategy;

import com.alon_gazit.messages.WhatAppMessageSender;
import com.alon_gazit.model.StockData;
import com.alon_gazit.model.SymbolMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alon.g on 10/9/2016.
 */
public class LongStrategy extends TrendStrategy{
    protected int numOfLongDays;
    protected int numOfStopDays;

    public LongStrategy(int numOfLongDays, int numOfStopDays, int numOfDaysForMovingAverage) {
        this.numOfLongDays = numOfLongDays;
        this.numOfStopDays = numOfStopDays;
        this.numOfDaysForMovingAverage = numOfDaysForMovingAverage;
    }

    @Override
    protected double getStrategyEntryPrice(List<String[]> symbolHistory) {
        return getMaxValue(symbolHistory,numOfLongDays);
    }

    @Override
    protected double getStrategyStopLost(List<String[]> symbolHistory) {
        return getMinValue(symbolHistory, numOfStopDays);
    }

    @Override
    public List<SymbolMessage> sendMessageDueToUpdate(double lastPriceUpdate, StockData stockData) {
        SymbolMessage message = null;
        if (stockData.getLastPrice()<lastPriceUpdate && stockData.getEntryPrice()<lastPriceUpdate){
            message = new SymbolMessage();
            message.setSymbol(stockData.getSymbol().getName());
            message.setText(" Pass "+ numOfLongDays +" days highest high. Enter Position");
            message.setPrice(lastPriceUpdate);
        } else if (stockData.getLastPrice()>lastPriceUpdate && stockData.getExitPrice()>lastPriceUpdate){
            message = new SymbolMessage();
            message.setSymbol(stockData.getSymbol().getName());
            message.setText(" Pass "+ numOfStopDays +" days lowest low. Exit Position");
            message.setPrice(lastPriceUpdate);
        }
        List<SymbolMessage> ansewr = new ArrayList<>();
        ansewr.add(message);
        return ansewr;
    }

    public int getNumOfLongDays() {return numOfLongDays;}

    public void setNumOfLongDays(int numOfLongDays) {this.numOfLongDays = numOfLongDays;}

    public int getNumOfStopDays() {return numOfStopDays;}

    public void setNumOfStopDays(int numOfStopDays) {this.numOfStopDays = numOfStopDays;}
}
