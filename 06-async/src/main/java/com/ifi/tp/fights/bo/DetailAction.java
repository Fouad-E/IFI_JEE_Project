package com.ifi.tp.fights.bo;


public class DetailAction {

    private int id;

    private int round;

    private String result;

    private String description;


    public DetailAction(int round, String description) {
        this.round = round;
        this.description = description;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }




}
