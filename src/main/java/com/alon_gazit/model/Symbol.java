package com.alon_gazit.model;

/**
 * Created by alon.g on 10/27/2016.
 */
public class Symbol {
    private int id;
    private String name;

    public Symbol(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
