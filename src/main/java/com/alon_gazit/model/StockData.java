package com.alon_gazit.model;

import com.alon_gazit.strategy.Strategy;

/**
 * Data structure
 */
public class StockData {
  private Symbol symbol;
  private Strategy strategy;
  private double lastPrice;
  private double entryPrice;
  private double exitPrice;
  private double range;
  private double positionSize;
  private double movingAverage;

  public double getMovingAverage() {
    return movingAverage;
  }

  public void setMovingAverage(double movingAverage) {
    this.movingAverage = movingAverage;
  }

  public Symbol getSymbol() {
    return symbol;
  }

  public void setSymbol(Symbol symbol) {
    this.symbol = symbol;
  }

  public double getLastPrice() {
    return lastPrice;
  }

  public void setLastPrice(double lastPrice) {
    this.lastPrice = lastPrice;
  }

  public double getPositionSize() {
    return positionSize;
  }

  public void setPositionSize(double positionSize) {
    this.positionSize = positionSize;
  }

  public double getRange() {
    return range;
  }

  public void setRange(double range) {
    this.range = range;
  }

  public double getExitPrice() {return exitPrice;}

  public void setExitPrice(double exitPrice) {this.exitPrice = exitPrice;}

  public double getEntryPrice() {return entryPrice;}

  public void setEntryPrice(double entryPrice) {this.entryPrice = entryPrice;}

  public Strategy getStrategy() {return strategy;}

  public void setStrategy(Strategy strategy) {this.strategy = strategy;}
}
