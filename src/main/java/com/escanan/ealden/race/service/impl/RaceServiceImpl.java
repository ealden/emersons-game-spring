package com.escanan.ealden.race.service.impl;

import com.escanan.ealden.race.data.RacerRepository;
import com.escanan.ealden.race.model.Racer;
import com.escanan.ealden.race.service.RaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RaceServiceImpl implements RaceService {
    @Autowired
    private RacerRepository racerRepository;

    @Value("${race.enable.testMode:false}")
    private boolean testMode;

    @Override
    public void newRace() {
        racerRepository.deleteAll();

        racerRepository.save(new Racer("Racer 1"));

        if (!testMode) {
            racerRepository.save(new Racer("Racer 2"));
            racerRepository.save(new Racer("Racer 3"));
            racerRepository.save(new Racer("Racer 4"));
        }
    }
}
