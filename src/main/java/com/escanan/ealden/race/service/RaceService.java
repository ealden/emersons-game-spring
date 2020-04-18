package com.escanan.ealden.race.service;

import com.escanan.ealden.race.model.Race;
import com.escanan.ealden.race.model.Racer.SpeedType;

public interface RaceService {
    Race getCurrentRace();
    Race newRace();
    Race save(Race race);
    Race roll(int roll, SpeedType speedType);
}
