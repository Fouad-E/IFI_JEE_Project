package com.ifi.tp.fights.service;


import com.ifi.tp.fights.bo.Fight;
import com.ifi.tp.trainers.bo.Trainer;

import java.util.List;

public interface FightService {

    Fight runFight(String trainerName1, String trainerName2);

    List<Fight> getFightsByTrainer(String trainer);


}
