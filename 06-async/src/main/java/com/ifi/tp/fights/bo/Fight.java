package com.ifi.tp.fights.bo;


import java.util.ArrayList;
import java.util.List;


public class Fight {


    private int id;

    private String trainer1;

    private String trainer2;

    private List<DetailAction> details;

    public Fight(String trainer1, String trainer2) {
        this.trainer1 = trainer1;
        this.trainer2 = trainer2;
        this.details = new ArrayList<DetailAction>();
    }

    public String getTrainer1() {
        return trainer1;
    }

    public void setTrainer1(String trainer1) {
        this.trainer1 = trainer1;
    }

    public String getTrainer2() {
        return trainer2;
    }

    public void setTrainer2(String trainer2) {
        this.trainer2 = trainer2;
    }

    public void setDetails(List<DetailAction> details) {
        this.details = details;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public List<DetailAction> getDetails() {
        return details;
    }



}
