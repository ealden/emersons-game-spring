package com.escanan.ealden.race.controller.api;

import com.escanan.ealden.race.controller.api.model.Race;
import com.escanan.ealden.race.controller.api.model.Roll;
import com.escanan.ealden.race.data.RacerRepository;
import com.escanan.ealden.race.model.Racer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class RacesController {
    @Autowired
    private RacerRepository racerRepository;

    @Value("${race.rolls.random:true}")
    private boolean randomRolls;

    @GetMapping("/api/races")
    public Race index() {
        List<Racer> racers = racerRepository.findAllByOrderByIdAsc();

        return new Race(racers, randomRolls);
    }

    @PostMapping("/api/races/roll")
    public Race roll(@RequestBody Roll roll) {
        Optional<Racer> result = racerRepository.findById(roll.getId());

        if (result.isPresent()) {
            Racer racer = result.get();

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
