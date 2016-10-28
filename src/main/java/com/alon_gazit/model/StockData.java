package com.alon_gazit.model;

/**
 * Data structure
 */
public class StockData {
  private Symbol symbol;
  private double lastPrice;
  private double entryPrice;
  private double exitPrice;
  private double range;
  private double positionSize;


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
}
