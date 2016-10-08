package com.alon_gazit.controllers;

/**
 * Data structure
 */
public class CalculationResult {
  private String symbol;
  private double lastPrice;
  private double _40DaysHigh;
  private double _20DaysHigh;
  private double _10DaysHigh;
  private double _40DaysLow;
  private double _20DaysLow;
  private double _10DaysLow;
  private double range;
  private double positionSize;


  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  public double getLastPrice() {
    return lastPrice;
  }

  public void setLastPrice(double lastPrice) {
    this.lastPrice = lastPrice;
  }

  public double get_40DaysHigh() {
    return _40DaysHigh;
  }

  public void set_40DaysHigh(double _40DaysHigh) {
    this._40DaysHigh = _40DaysHigh;
  }

  public double get_20DaysHigh() {
    return _20DaysHigh;
  }

  public void set_20DaysHigh(double _20DaysHigh) {
    this._20DaysHigh = _20DaysHigh;
  }

  public double get_10DaysHigh() {
    return _10DaysHigh;
  }

  public void set_10DaysHigh(double _10DaysHigh) {
    this._10DaysHigh = _10DaysHigh;
  }

  public double get_40DaysLow() {
    return _40DaysLow;
  }

  public void set_40DaysLow(double _40DaysLow) {
    this._40DaysLow = _40DaysLow;
  }

  public double get_20DaysLow() {
    return _20DaysLow;
  }

  public void set_20DaysLow(double _20DaysLow) {
    this._20DaysLow = _20DaysLow;
  }

  public double get_10DaysLow() {
    return _10DaysLow;
  }

  public void set_10DaysLow(double _10DaysLow) {
    this._10DaysLow = _10DaysLow;
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
}
