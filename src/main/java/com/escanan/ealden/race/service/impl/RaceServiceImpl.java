package com.escanan.ealden.race.service.impl;

import com.escanan.ealden.race.data.RaceRepository;
import com.escanan.ealden.race.data.RacerRepository;
import com.escanan.ealden.race.model.Race;
import com.escanan.ealden.race.model.Racer;
import com.escanan.ealden.race.service.RaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RaceServiceImpl implements RaceService {
    @Autowired
    private RaceRepository raceRepository;

    @Autowired
    private RacerRepository racerRepository;

    @Value("${race.enable.testMode:false}")
    private boolean testMode;

    @Override
    public Race getCurrentRace() {
        List<Racer> racers = racerRepository.findAllByOrderByIdAsc();

        return new Race(racers);
    }

    @Override
    public void newRace() {
        raceRepository.deleteAll();
        racerRepository.deleteAll();

        Race race = new Race();
        race.addRacer(new Racer("Racer 1"));

        if (!testMode) {
            race.addRacer(new Racer("Racer 2"));
            race.addRacer(new Racer("Racer 3"));
            race.addRacer(new Racer("Racer 4"));
        }

        raceRepository.save(race);
    }
}
