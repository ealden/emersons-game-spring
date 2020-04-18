package com.escanan.ealden.race.controller.api;

import com.escanan.ealden.race.controller.api.model.Roll;
import com.escanan.ealden.race.controller.api.model.Settings;
import com.escanan.ealden.race.data.RacerRepository;
import com.escanan.ealden.race.model.Race;
import com.escanan.ealden.race.model.Racer;
import com.escanan.ealden.race.service.RaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class RacesController {
    @Autowired
    private RacerRepository racerRepository;

    @Autowired
    private RaceService raceService;

    @Value("${race.enable.testMode:false}")
    private boolean testMode;

    @GetMapping("/api/races")
    public Race index() {
        return raceService.getCurrentRace();
    }

    @PostMapping("/api/races/roll")
    public Race roll(@RequestBody Roll roll) {
        Optional<Racer> result = racerRepository.findById(roll.getId());

        if (result.isPresent()) {
            Racer racer = result.get();

            if (!testMode) {
                racer.move(roll.getSpeedType());
            } else {
                racer.move(roll.getRoll(), roll.getSpeedType());
            }

            racerRepository.save(racer);
        }

        return index();
    }

    @PostMapping("/api/races/new")
    public Race newRace() {
        raceService.newRace();

        return index();
    }

    @GetMapping("/api/races/settings")
    public Settings settings() {
        Settings settings = new Settings();
        settings.setTestMode(testMode);

        return settings;
    }
}
