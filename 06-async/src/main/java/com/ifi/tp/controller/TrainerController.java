package com.ifi.tp.controller;

import com.ifi.tp.fights.bo.Fight;
import com.ifi.tp.fights.service.FightService;
import com.ifi.tp.trainers.service.TrainerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TrainerController {

    @Autowired
    private TrainerService trainerService;

    @Autowired
    private FightService fightService;

    @GetMapping("/trainers")
    ModelAndView index(){
        var modelAndView = new ModelAndView("trainers");

        modelAndView.addObject("trainers", trainerService.getAllTrainers());

        return modelAndView;
    }

    @GetMapping("/trainers/{name}")
    ModelAndView index(@PathVariable String name){
        var modelAndView = new ModelAndView("trainer");

        modelAndView.addObject("trainer", trainerService.getTrainer(name));

        return modelAndView;
    }




    @PostMapping("/trainers/{trainer1}/fight/{trainer2}")
    Fight runFight(@PathVariable String trainer1, @PathVariable String trainer2){

        return this.fightService.runFight(trainer1, trainer2);
    }


    @GetMapping("/trainers/{trainer}/fights")
    ModelAndView getFightsByTrainer(@PathVariable String trainer){
        var modelAndView = new ModelAndView("fightsDetails");

        modelAndView.addObject("fights", this.fightService.getFightsByTrainer(trainer));

        return modelAndView;
    }


    /*
    @GetMapping("/trainers/{trainer}/arena")
    ModelAndView getFightsByTrainer(@PathVariable String trainer){
        var modelAndView = new ModelAndView("TrainersArena");
        var trainers = [];
        var fights[] = this.fightService.getFightsByTrainer(trainer);


        modelAndView.addObject("fights", this.fightService.getFightsByTrainer(trainer));

        return modelAndView;
    }
    */

}
