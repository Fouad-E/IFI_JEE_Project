package com.ifi.tp.controller;


import com.ifi.tp.fights.bo.DetailAction;
import com.ifi.tp.fights.bo.Fight;
import com.ifi.tp.fights.service.FightService;
import com.ifi.tp.trainers.service.TrainerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class FightController {


    private FightService fightService;
    private TrainerService trainerService;

    public FightController(FightService fightService, TrainerService trainerService) {
        this.fightService = fightService;
        this.trainerService = trainerService;
    }

    @PostMapping("/fights/{trainer1}/{trainer2}")
    public void fights(@PathVariable("trainer1") String trainerParam1, @PathVariable("trainer2") String trainerParam2){

        this.fightService.runFight(trainerParam1, trainerParam2).getDetails();


        /*
        var modelAndView = new ModelAndView("fightsDetails");

        modelAndView.addObject("details", this.fightService.runFight(trainerParam1, trainerParam2).getDetails());
        modelAndView.addObject("trainer1", trainerParam1);
        modelAndView.addObject("trainer2", trainerParam2);

        return modelAndView;
        */
    }

    @GetMapping("/fights/{trainer}")
    public List<Fight> getFightsByTrainer(@PathVariable("trainer") String trainerParam){
        return this.fightService.getFightsByTrainer(trainerParam);
    }
}
