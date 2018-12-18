package com.ifi.tp.fights.service;

import com.ifi.tp.fights.bo.DetailAction;
import com.ifi.tp.fights.bo.Fight;
import com.ifi.tp.fights.repository.FightRepository;
import com.ifi.tp.notification.service.NotificationService;
import com.ifi.tp.trainers.bo.Pokemon;
import com.ifi.tp.trainers.bo.Trainer;
import com.ifi.tp.trainers.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;


import java.util.List;

@Service
public class FightServiceImpl implements  FightService{

    private TrainerService trainerService;
    private NotificationService notificationService;

    private FightRepository fightRepository;

    private Trainer trainer1;
    private Trainer trainer2;

    private Fight fight;

    public FightServiceImpl(FightRepository fightRepository, TrainerService trainerService, NotificationService notificationService){
        this.fightRepository = fightRepository;
        this.trainerService = trainerService;
        this.notificationService = notificationService;
    }

    @Override
    public List<Fight> getFightsByTrainer(String trainerName){
        Trainer trainer = trainerService.getTrainer(trainerName);

        List<Fight> fights = new ArrayList<Fight>();
        for(Fight f : fightRepository.findAll()){
            if(trainer.getName().equals(trainerService.getTrainer((f.getTrainer1())).getName())){
                fights.add(f);
            }
        }
        return fights;
    }





    @Override
    public Fight runFight(String trainerName1, String trainerName2){
        this.trainer1 = this.trainerService.getTrainer(trainerName1);
        this.trainer2 = this.trainerService.getTrainer(trainerName2);

        this.fight = new Fight(trainerName1, trainerName2);

        this.beginFight();

        this.fightRepository.save(fight);

        return fight;
    }

    private void beginFight(){
        List<Pokemon> pokemonsTrainer1 = trainer1.getTeam();
        List<Pokemon> pokemonsTrainer2 = trainer2.getTeam();

        int iTrainerPok1 = 0;
        int jTrainerPok2 = 0;
        int trainerAttack = 0;

        // 1er pokemon du trainer 1
        Pokemon pokemonTrainer1 = pokemonsTrainer1.get(0);

        // 1er pokemon du trainer 2
        Pokemon pokemonTrainer2 = pokemonsTrainer2.get(0);

        int round = 0;
        String description;
        int hp;
        List<DetailAction> detailsAction = new ArrayList<DetailAction>();

        while(iTrainerPok1 != pokemonsTrainer1.size() && jTrainerPok2 != pokemonsTrainer2.size()){

            round++;

            if(fasterSpeed(pokemonTrainer1, pokemonTrainer2)){
                description = pokemonTrainer1.getType().getName()+ " hits first";
                detailsAction.add(new DetailAction(round, description));
                this.notificationService.sendNotification("Round "+round+" : "+description);
                trainerAttack = 1;
            }else{
                description = pokemonTrainer2.getType().getName()+ " hits first";
                detailsAction.add(new DetailAction(round, description));
                this.notificationService.sendNotification("Round "+round+" : "+description);
                trainerAttack = 2;
            }

            // Lifes of twos pokemons not null
            while(pokemonTrainer1.getHp()<=0 && pokemonTrainer2.getHp()<=0){
                round++;
                // Pokemon1 attacks Pokemon2
                if(trainerAttack==1){
                    // Update hp
                    hp = attackAndUpdateHp(pokemonTrainer1, pokemonTrainer2);
                    description = pokemonTrainer1.getType().getName()+ " hits. "+pokemonTrainer2.getType().getName()+" loses "+hp+" HP.";
                    detailsAction.add(new DetailAction(round, description));
                    this.notificationService.sendNotification("Round "+round+" : "+description);
                    // Change pass pokemon
                    trainerAttack = 2;
                }
                // Pokemon1 attacks Pokemon2
                else{
                    // Update Hp
                    hp = attackAndUpdateHp(pokemonTrainer2, pokemonTrainer1);
                    description = pokemonTrainer2.getType().getName()+ " hits. "+pokemonTrainer1.getType().getName()+" loses "+hp+" HP.";
                    detailsAction.add(new DetailAction(round, description));
                    this.notificationService.sendNotification("Round "+round+" : "+description);
                    // Change pass pokemon
                    trainerAttack = 1;
                }

            }

            round++;

            if(pokemonTrainer1.getHp()<=0){
                description = pokemonTrainer1.getType().getName() + " is KO ! "+pokemonsTrainer1.get(iTrainerPok1+1).getType().getName() + " enters battle !";
                detailsAction.add(new DetailAction(round, description));
                this.notificationService.sendNotification("Round "+round+" : "+description);
                iTrainerPok1++;
                pokemonTrainer1 = pokemonsTrainer1.get(iTrainerPok1);
            }
            if(pokemonTrainer2.getHp()<=0){
                description = pokemonTrainer2.getType().getName() + " is KO ! "+pokemonsTrainer2.get(jTrainerPok2+1).getType().getName() + "enters battle ! ";
                detailsAction.add(new DetailAction(round, description));
                this.notificationService.sendNotification("Round "+round+" : "+description);
                jTrainerPok2++;
                pokemonTrainer1 = pokemonsTrainer1.get(jTrainerPok2);
            }
        }

        round++;

        if(iTrainerPok1>jTrainerPok2){
            description = trainer1.getName()+" is KO ! "+trainer2.getName()+" wins !" ;
            detailsAction.add(new DetailAction(round, description));
            this.notificationService.sendNotification("Round "+round+" : "+description);
        }
        else if(iTrainerPok1 < jTrainerPok2){
            description = trainer2.getName()+" is KO ! "+trainer1.getName()+" wins !" ;
            detailsAction.add(new DetailAction(round, description));
            this.notificationService.sendNotification("Round "+round+" : "+description);
        }
        else{
            description = "Fight between "+trainer1.getName()+" and "+trainer1.getName()+" is NULL !" ;
            detailsAction.add(new DetailAction(round, description));
            this.notificationService.sendNotification("Round "+round+" : "+description);
        }

        this.fight.setDetails(detailsAction);

    }


    private boolean fasterSpeed(Pokemon pokemon1, Pokemon pokemon2){
        return (pokemon1.getLevel()+pokemon1.getType().getStats().getSpeed()) > (pokemon2.getLevel()+pokemon2.getType().getStats().getSpeed());
    }

    private int attackAndUpdateHp(Pokemon pokemon1, Pokemon pokemon2){
        int hp = (pokemon1.getLevel() + pokemon1.getType().getStats().getAttack()) - (pokemon2.getLevel() + pokemon2.getType().getStats().getDefense());

        if(hp < 0){
            hp = 0;
        }
        pokemon1.setHp(pokemon1.getHp() - hp);

        return hp;
    }


    @Autowired
    void setTrainerService(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @Autowired
    void setNotificationService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }
}
