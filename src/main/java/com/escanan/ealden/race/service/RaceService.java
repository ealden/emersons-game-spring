package com.escanan.ealden.race.service;

import com.escanan.ealden.race.model.Race;

public interface RaceService {
    Race getCurrentRace();
    Race newRace();
    Race save(Race race);
}
