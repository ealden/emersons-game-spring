package com.escanan.ealden.race.service.impl;

import com.escanan.ealden.race.data.RacerRepository;
import com.escanan.ealden.race.model.Racer;
import com.escanan.ealden.race.service.RaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RaceServiceImpl implements RaceService {
    @Autowired
    private RacerRepository racerRepository;

    @Override
    public void newRace() {
        racerRepository.deleteAll();

        racerRepository.save(new Racer("Racer 1"));
        racerRepository.save(new Racer("Racer 2"));
        racerRepository.save(new Racer("Racer 3"));
        racerRepository.save(new Racer("Racer 4"));
    }
}
