package com.escanan.ealden.race.data;

import com.escanan.ealden.race.model.Racer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface RacerRepository extends CrudRepository<Racer, Long>, PagingAndSortingRepository<Racer, Long> {
    public List<Racer> findAllByOrderByIdAsc();
}
