package com.ifi.tp.fights.repository;

import com.ifi.tp.fights.bo.DetailAction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailActionRepository extends CrudRepository<DetailAction, Integer> {
}
