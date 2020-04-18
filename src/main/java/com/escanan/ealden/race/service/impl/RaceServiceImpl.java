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
    public Race newRace() {
        raceRepository.deleteAll();

        Race race = new Race();
        race.addRacer(new Racer("Alice"));
        race.addRacer(new Racer("Bob"));
        race.addRacer(new Racer("Charlie"));
        race.addRacer(new Racer("Dave"));

        return raceRepository.save(race);
    }

    @Override
    public Race save(Race race) {
        return raceRepository.save(race);
    }

    @Override
    public Race roll(int roll, Racer.SpeedType speedType) {
        Race race = getCurrentRace();

        if (!testMode) {
            race.roll(speedType);
        } else {
            race.roll(roll, speedType);
        }

        return save(race);
    }
}
