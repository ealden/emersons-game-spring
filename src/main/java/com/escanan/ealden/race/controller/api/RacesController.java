package com.escanan.ealden.race.controller.api;

import com.escanan.ealden.race.controller.api.model.Race;
import com.escanan.ealden.race.controller.api.model.Roll;
import com.escanan.ealden.race.data.RacerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RacesController {
    @Autowired
    private RacerRepository racerRepository;

    @Value("${race.rolls.random:true}")
    private boolean randomRolls;

    @GetMapping("/api/races")
    public Race index() {
        var racers = racerRepository.findAll();

        return new Race(racers, randomRolls);
    }

    @PostMapping("/api/races/roll")
    public Race roll(@RequestBody Roll roll) {
        var result = racerRepository.findById(roll.getId());

        if (result.isPresent()) {
            var racer = result.get();

            if (randomRolls) {
                racer.move(roll.getSpeedType());
            } else {
                racer.move(roll.getRoll(), roll.getSpeedType());
            }

            racerRepository.save(racer);
        }

        return index();
    }
}
