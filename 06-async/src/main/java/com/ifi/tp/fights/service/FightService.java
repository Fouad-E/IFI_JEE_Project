package com.ifi.tp.fights.service;

import com.ifi.tp.fights.bo.Fight;

public interface FightService {

    Fight runFight(String trainerName1, String trainerName2);

    Fight[] getFightsByTrainer(String trainer);

}
