package com.ifi.tp.fights.service;

import com.ifi.tp.fights.bo.Fight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FightServiceImpl implements FightService{

    private RestTemplate restTemplate;

    @Autowired
    public FightServiceImpl(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }


    @Override
    public Fight runFight(String trainerName1, String trainerName2) {
        var url = "http://localhost:9000/fights/"+trainerName1+"/"+trainerName2;
        var fight = restTemplate.getForObject(url, Fight.class);
        return fight;
    }

    @Override
    public Fight[] getFightsByTrainer(String trainer) {
        var url ="http://localhost:9000/fights/"+trainer;
        Fight fight[] = restTemplate.getForObject(url, Fight[].class);
        return fight;
    }
}
