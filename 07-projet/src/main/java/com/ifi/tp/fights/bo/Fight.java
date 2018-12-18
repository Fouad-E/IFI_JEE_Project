package com.ifi.tp.fights.bo;

import com.ifi.tp.trainers.bo.Pokemon;
import com.ifi.tp.trainers.bo.Trainer;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Entity
public class Fight {

    @Id
    @GeneratedValue
    private int id;

    private String trainer1;

    private String trainer2;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
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
