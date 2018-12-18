package com.ifi.tp.fights.repository;

import com.ifi.tp.fights.bo.Fight;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FightRepository extends CrudRepository<Fight, Integer> {
}
