package com.ifi.tp.trainers.service;

import com.ifi.tp.pokemonTypes.service.PokemonService;
import com.ifi.tp.trainers.bo.Pokemon;
import com.ifi.tp.trainers.bo.Trainer;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TrainerServiceImplTest {

    @Test
    void getAllTrainers_shouldCallTheRemoteService() {
        var restTemplate = mock(RestTemplate.class);
        var trainerServiceImpl = new TrainerServiceImpl(restTemplate);
        trainerServiceImpl.setTrainerServiceUrl("http://localhost:8081");

        var expectedUrl = "http://localhost:8081/trainers";
        var trainers = new Trainer[]{new Trainer(), new Trainer()};
        when(restTemplate.getForObject(expectedUrl, Trainer[].class)).thenReturn(trainers);

        var result = trainerServiceImpl.getAllTrainers();

        assertNotNull(result);
        assertEquals(2, result.size());

        verify(restTemplate).getForObject("http://localhost:8081/trainers", Trainer[].class);
    }

    @Test
    void getTrainer_shouldCallTheRemoteService() {
        var restTemplate = mock(RestTemplate.class);
        var trainerServiceImpl = new TrainerServiceImpl(restTemplate);
        trainerServiceImpl.setTrainerServiceUrl("http://localhost:8081");

        var pokemonService = mock(PokemonService.class);
        trainerServiceImpl.setPokemonService(pokemonService);

        var expectedUrl = "http://localhost:8081/trainers/{name}";

        var ash = new Trainer();
        var pikachu = new Pokemon();
        pikachu.setPokemonNumber(25);
        ash.setTeam(List.of(pikachu));

        when(restTemplate.getForObject(expectedUrl, Trainer.class, "Ash")).thenReturn(ash);

        var result = trainerServiceImpl.getTrainer("Ash");

        assertNotNull(result);

        verify(restTemplate).getForObject("http://localhost:8081/trainers/{name}", Trainer.class, "Ash");
    }

    @Test
    void getTrainer_shouldCallTheRemoteService_andEnrichPokemonData() {
        var restTemplate = mock(RestTemplate.class);
        var trainerServiceImpl = new TrainerServiceImpl(restTemplate);
        trainerServiceImpl.setTrainerServiceUrl("http://localhost:8081");

        var pokemonService = mock(PokemonService.class);
        trainerServiceImpl.setPokemonService(pokemonService);

        var expectedUrl = "http://localhost:8081/trainers/{name}";

        var ash = new Trainer();
        var pikachu = new Pokemon();
        pikachu.setPokemonNumber(25);
        ash.setTeam(List.of(pikachu));

        when(restTemplate.getForObject(expectedUrl, Trainer.class, "Ash")).thenReturn(ash);

        var result = trainerServiceImpl.getTrainer("Ash");

        assertNotNull(result);

        verify(restTemplate).getForObject("http://localhost:8081/trainers/{name}", Trainer.class, "Ash");
        verify(pokemonService).getPokemonType(25);
    }
}