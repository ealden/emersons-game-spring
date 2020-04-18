package com.escanan.ealden.race.data;

import com.escanan.ealden.race.model.Race;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RaceRepository extends CrudRepository<Race, Long>, PagingAndSortingRepository<Race, Long> {
}
