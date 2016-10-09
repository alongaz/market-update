package com.alon_gazit.model;

/**
 * Created by alon.g on 10/8/2016.
 */
public class SymbolMessage {
    private String symbol;
    private double price;
    private String text;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getMessage(){
        return "The symbol " + symbol +
                text +
                " The current price is: " + price;
    }
}
