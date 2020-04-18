package com.escanan.ealden.race.service.impl;

import com.escanan.ealden.race.data.RaceRepository;
import com.escanan.ealden.race.model.Race;
import com.escanan.ealden.race.model.Racer;
import com.escanan.ealden.race.service.RaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RaceServiceImpl implements RaceService {
    @Autowired
    private RaceRepository raceRepository;

    @Value("${race.enable.testMode:false}")
    private boolean testMode;

    @Override
    public Race getCurrentRace() {
        return raceRepository.findAll().iterator().next();
    }

    @Override
    public void newRace() {
        raceRepository.deleteAll();

        Race race = new Race();
        race.addRacer(new Racer("Racer 1"));

        if (!testMode) {
            race.addRacer(new Racer("Racer 2"));
            race.addRacer(new Racer("Racer 3"));
            race.addRacer(new Racer("Racer 4"));
        }

        raceRepository.save(race);
    }

    @Override
    public Race save(Race race) {
        return raceRepository.save(race);
    }
}
