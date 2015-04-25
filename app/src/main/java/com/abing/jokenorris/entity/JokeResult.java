package com.abing.jokenorris.entity;

/**
 * Created by Catingub on 4/24/2015.
 */
public class JokeResult {

    public JokeResult(){

    }

    String type;
    private JokeContent value;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public JokeContent getValue() {
        return value;
    }

    public void setValue(JokeContent value) {
        this.value = value;
    }

}
